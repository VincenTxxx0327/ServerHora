(function() {
    var API_BASE = '/spba-api/article';

    var params = new URLSearchParams(window.location.search);
    var articleId = params.get('id');

    var loadingEl = document.getElementById('article-loading');
    var errorEl = document.getElementById('article-error');
    var bodyEl = document.getElementById('article-body');
    var titleEl = document.getElementById('article-title');
    var subtitleEl = document.getElementById('article-subtitle');
    var categoryEl = document.getElementById('article-category');
    var dateEl = document.getElementById('article-date');
    var authorEl = document.getElementById('article-author');
    var sidebarAuthorEl = document.getElementById('sidebar-author');
    var markdownEl = document.getElementById('markdown-content');
    var tocContainer = document.getElementById('toc-container');
    var tocNav = document.getElementById('toc-nav');
    var relatedEl = document.getElementById('related-articles');
    var btnCopyLink = document.getElementById('btn-copy-link');

    var categoryColors = {
        'ESSAY': 'bg-tertiary text-white',
        'REVIEW': 'bg-secondary text-white',
        'PROJECT': 'bg-tertiary text-white',
        'TECH': 'bg-on-background text-white'
    };

    function formatDate(dateStr) {
        if (!dateStr) return '';
        var d = new Date(dateStr);
        var months = ['JAN','FEB','MAR','APR','MAY','JUN','JUL','AUG','SEP','OCT','NOV','DEC'];
        return months[d.getMonth()] + ' ' + (d.getDate() < 10 ? '0' : '') + d.getDate() + ', ' + d.getFullYear();
    }

    function escapeHtml(str) {
        if (!str) return '';
        var div = document.createElement('div');
        div.appendChild(document.createTextNode(str));
        return div.innerHTML;
    }

    function renderMarkdown(content) {
        if (!content) return '';

        if (typeof marked === 'undefined') {
            return '<pre style="white-space:pre-wrap;word-break:break-word;">' + escapeHtml(content) + '</pre>';
        }

        try {
            var renderer = new marked.Renderer();

            renderer.code = function(obj) {
                var text, lang;
                if (obj && typeof obj === 'object') {
                    text = obj.text || '';
                    lang = obj.lang || '';
                } else {
                    text = obj || '';
                    lang = arguments[1] || '';
                }

                var langLabel = lang ? '<div class="flex items-center justify-between px-4 py-2 bg-[#2d2f2f] border-b border-[#3a3a4a]"><span class="font-mono text-[10px] uppercase tracking-widest text-[#fde400]">' + escapeHtml(lang) + '</span></div>' : '';

                var highlighted = '';
                if (typeof hljs !== 'undefined' && lang && hljs.getLanguage(lang)) {
                    try {
                        highlighted = hljs.highlight(text, { language: lang }).value;
                    } catch (e) {
                        highlighted = escapeHtml(text);
                    }
                } else {
                    highlighted = escapeHtml(text);
                }

                return '<pre>' + langLabel + '<code class="hljs language-' + escapeHtml(lang) + '">' + highlighted + '</code></pre>';
            };

            marked.use({ renderer: renderer, gfm: true, breaks: true });

            return marked.parse(content);
        } catch (e) {
            console.error('Markdown渲染失败:', e);
            return '<pre style="white-space:pre-wrap;word-break:break-word;">' + escapeHtml(content) + '</pre>';
        }
    }

    function buildToc() {
        var headings = markdownEl.querySelectorAll('h1, h2, h3');
        if (headings.length === 0) {
            tocContainer.classList.add('hidden');
            return;
        }

        tocContainer.classList.remove('hidden');
        tocNav.innerHTML = '';

        headings.forEach(function(heading, index) {
            var id = 'heading-' + index;
            heading.id = id;

            var level = parseInt(heading.tagName.charAt(1));
            var item = document.createElement('a');
            item.className = 'toc-item block font-mono text-xs py-1.5 px-2 text-on-surface-variant hover:text-on-background';
            item.style.paddingLeft = ((level - 1) * 12 + 8) + 'px';
            item.textContent = heading.textContent;
            item.href = '#' + id;
            item.addEventListener('click', function(e) {
                e.preventDefault();
                heading.scrollIntoView({ behavior: 'smooth', block: 'start' });
            });
            tocNav.appendChild(item);
        });
    }

    function loadRelatedArticles(currentCategory) {
        var url = API_BASE + '/list?page=1&size=5';
        if (currentCategory) {
            url += '&category=' + encodeURIComponent(currentCategory);
        }

        fetch(url)
            .then(function(res) { return res.json(); })
            .then(function(res) {
                if (res.code !== 200 || !res.data || !res.data.records) return;
                var records = res.data.records.filter(function(r) {
                    return String(r.id) !== String(articleId);
                }).slice(0, 4);

                relatedEl.innerHTML = '';
                records.forEach(function(item) {
                    var a = document.createElement('a');
                    a.href = 'article-detail.html?id=' + item.id;
                    a.className = 'block font-mono text-xs py-1.5 text-on-surface-variant hover:text-on-background hover:bg-primary-container/30 px-1 transition-all truncate';
                    a.textContent = item.title;
                    relatedEl.appendChild(a);
                });

                if (records.length === 0) {
                    relatedEl.innerHTML = '<p class="font-mono text-[10px] text-on-surface-variant">暂无相关文章</p>';
                }
            })
            .catch(function() {});
    }

    function loadArticle() {
        if (!articleId) {
            showError();
            return;
        }

        loadingEl.classList.remove('hidden');
        errorEl.classList.add('hidden');
        bodyEl.classList.add('hidden');

        fetch(API_BASE + '/' + articleId)
            .then(function(res) { return res.json(); })
            .then(function(res) {
                loadingEl.classList.add('hidden');

                if (res.code !== 200 || !res.data) {
                    showError();
                    return;
                }

                renderArticle(res.data);
            })
            .catch(function(err) {
                loadingEl.classList.add('hidden');
                showError();
                console.error('加载文章失败:', err);
            });
    }

    function showError() {
        errorEl.classList.remove('hidden');
        loadingEl.classList.add('hidden');
        bodyEl.classList.add('hidden');
    }

    function renderArticle(article) {
        document.title = article.title + ' - THE ENGINEER';

        titleEl.textContent = article.title || '';
        subtitleEl.textContent = article.subtitle || '';

        var cat = article.category || 'TECH';
        categoryEl.textContent = cat;
        var catClass = categoryColors[cat] || 'bg-on-background text-white';
        categoryEl.className = 'font-mono text-xs font-bold px-2 py-1 uppercase tracking-widest ' + catClass;

        dateEl.textContent = formatDate(article.createTime);
        authorEl.textContent = article.author || 'VincenT';
        sidebarAuthorEl.textContent = article.author || 'VincenT';

        markdownEl.innerHTML = renderMarkdown(article.content);

        bodyEl.classList.remove('hidden');
        errorEl.classList.add('hidden');

        buildToc();
        loadRelatedArticles(cat);
    }

    btnCopyLink.addEventListener('click', function() {
        var url = window.location.href;
        if (navigator.clipboard) {
            navigator.clipboard.writeText(url).then(function() {
                showToast('链接已复制');
            });
        } else {
            var input = document.createElement('input');
            input.value = url;
            document.body.appendChild(input);
            input.select();
            document.execCommand('copy');
            document.body.removeChild(input);
            showToast('链接已复制');
        }
    });

    function showToast(msg) {
        var existing = document.getElementById('copy-toast');
        if (existing) existing.remove();

        var toast = document.createElement('div');
        toast.id = 'copy-toast';
        toast.className = 'fixed top-20 left-1/2 -translate-x-1/2 bg-on-background text-background font-mono font-bold text-sm px-6 py-3 border-2 border-black shadow-[4px_4px_0px_0px_rgba(0,0,0,1)] z-[100] transition-all';
        toast.textContent = msg;
        document.body.appendChild(toast);

        setTimeout(function() {
            toast.style.opacity = '0';
            toast.style.transform = 'translateX(-50%) translateY(-10px)';
            setTimeout(function() { toast.remove(); }, 300);
        }, 1500);
    }

    loadArticle();
})();
