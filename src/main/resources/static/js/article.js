(function() {
    var API_BASE = '/spba-api/article';
    var currentPage = 1;
    var totalPages = 1;
    var currentCategory = '';
    var PAGE_SIZE = 6;

    var grid = document.getElementById('article-grid');
    var loading = document.getElementById('loading');
    var emptyState = document.getElementById('empty-state');
    var pageInfo = document.getElementById('page-info');
    var btnPrev = document.getElementById('btn-prev');
    var btnNext = document.getElementById('btn-next');

    var categoryColors = {
        'ESSAY': 'bg-tertiary text-white',
        'REVIEW': 'bg-secondary text-white',
        'PROJECT': 'bg-tertiary text-white',
        'TECH': 'bg-on-background text-white'
    };

    var cardBgs = ['bg-white', 'bg-white', 'bg-secondary-container', 'bg-primary-container', 'bg-white', 'bg-white'];

    function formatDate(dateStr) {
        if (!dateStr) return '';
        var d = new Date(dateStr);
        var months = ['JAN','FEB','MAR','APR','MAY','JUN','JUL','AUG','SEP','OCT','NOV','DEC'];
        return months[d.getMonth()] + ' ' + (d.getDate() < 10 ? '0' : '') + d.getDate() + ', ' + d.getFullYear();
    }

    function renderArticle(item, index) {
        var cat = item.category || 'TECH';
        var catClass = categoryColors[cat] || 'bg-on-background text-white';
        var bgClass = cardBgs[index % cardBgs.length];
        var dateStr = formatDate(item.createTime);
        var subtitle = item.subtitle || '';

        var article = document.createElement('article');
        article.className = bgClass + ' border-4 border-black p-6 shadow-[8px_8px_0px_0px_rgba(0,0,0,1)] hover:translate-x-[2px] hover:translate-y-[2px] hover:shadow-[6px_6px_0px_0px_rgba(0,0,0,1)] transition-all flex flex-col h-full';

        article.innerHTML =
            '<div class="mb-4 flex items-center justify-between">' +
                '<span class="font-mono text-xs font-bold ' + catClass + ' px-2 py-1 uppercase tracking-widest">' + cat + '</span>' +
                '<span class="font-mono text-[10px] uppercase font-bold tracking-widest text-on-surface-variant">' + dateStr + '</span>' +
            '</div>' +
            '<h2 class="font-headline font-black text-2xl leading-tight mb-4">' + item.title + '</h2>' +
            '<p class="font-body text-sm mb-6 flex-grow text-on-surface-variant">' + subtitle + '</p>' +
            '<div class="mt-auto pt-4 border-t-2 border-black/10">' +
                '<a class="inline-flex items-center gap-2 font-mono font-bold uppercase tracking-widest text-xs group/link" href="article-detail.html?id=' + item.id + '">' +
                    '阅读文章' +
                    '<span class="material-symbols-outlined text-sm group-hover/link:translate-x-1 transition-transform">arrow_forward</span>' +
                '</a>' +
            '</div>';

        return article;
    }

    function loadArticles() {
        grid.innerHTML = '';
        loading.classList.remove('hidden');
        emptyState.classList.add('hidden');

        var url = API_BASE + '/list?page=' + currentPage + '&size=' + PAGE_SIZE;
        if (currentCategory) {
            url += '&category=' + encodeURIComponent(currentCategory);
        }

        fetch(url)
            .then(function(res) { return res.json(); })
            .then(function(res) {
                loading.classList.add('hidden');
                if (res.code !== 200 || !res.data) {
                    emptyState.classList.remove('hidden');
                    return;
                }
                var data = res.data;
                var records = data.records || [];
                totalPages = data.pages || 1;

                if (records.length === 0) {
                    emptyState.classList.remove('hidden');
                } else {
                    records.forEach(function(item, i) {
                        grid.appendChild(renderArticle(item, i));
                    });
                }

                updatePagination();
            })
            .catch(function(err) {
                loading.classList.add('hidden');
                emptyState.classList.remove('hidden');
                console.error('加载文章失败:', err);
            });
    }

    function updatePagination() {
        var pageText = 'PAGE ' + (currentPage < 10 ? '0' : '') + currentPage + ' / ' + (totalPages < 10 ? '0' : '') + totalPages;
        pageInfo.textContent = pageText;
        btnPrev.disabled = currentPage <= 1;
        btnNext.disabled = currentPage >= totalPages;
    }

    btnPrev.addEventListener('click', function() {
        if (currentPage > 1) {
            currentPage--;
            loadArticles();
            window.scrollTo({ top: 0, behavior: 'smooth' });
        }
    });

    btnNext.addEventListener('click', function() {
        if (currentPage < totalPages) {
            currentPage++;
            loadArticles();
            window.scrollTo({ top: 0, behavior: 'smooth' });
        }
    });

    document.querySelectorAll('.filter-btn').forEach(function(btn) {
        btn.addEventListener('click', function() {
            document.querySelectorAll('.filter-btn').forEach(function(b) {
                b.classList.remove('filter-btn-active');
                b.classList.add('bg-white');
            });
            this.classList.add('filter-btn-active');
            this.classList.remove('bg-white');
            currentCategory = this.getAttribute('data-category');
            currentPage = 1;
            loadArticles();
        });
    });

    loadArticles();
})();
