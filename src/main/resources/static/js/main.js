document.addEventListener('DOMContentLoaded', function() {
    initSmoothScroll();
    initScrollAnimations();
    initCardHoverEffects();
    initTrackEvents();
});

function initSmoothScroll() {
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            e.preventDefault();
            const targetId = this.getAttribute('href');
            if (targetId === '#') return;
            
            const targetElement = document.querySelector(targetId);
            if (targetElement) {
                targetElement.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
}

function initScrollAnimations() {
    const observerOptions = {
        root: null,
        rootMargin: '0px',
        threshold: 0.1
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, observerOptions);

    document.querySelectorAll('.project-card, .article-item, .video-card').forEach(el => {
        el.style.opacity = '0';
        el.style.transform = 'translateY(20px)';
        el.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
        observer.observe(el);
    });
}

function initCardHoverEffects() {
    const cards = document.querySelectorAll('.project-card, .video-card');
    
    cards.forEach(card => {
        card.addEventListener('click', function() {
            this.style.transform = 'scale(0.98)';
            setTimeout(() => {
                this.style.transform = '';
            }, 150);
        });
    });

    const articles = document.querySelectorAll('.article-item');
    articles.forEach(article => {
        article.addEventListener('click', function() {
            console.log('Article clicked:', this.querySelector('.article-title').textContent);
        });
    });
}

function typeWriter(element, text, speed = 50) {
    let i = 0;
    element.textContent = '';
    
    function type() {
        if (i < text.length) {
            element.textContent += text.charAt(i);
            i++;
            setTimeout(type, speed);
        }
    }
    
    type();
}

function addParallaxEffect() {
    window.addEventListener('scroll', () => {
        const scrolled = window.pageYOffset;
        const hero = document.querySelector('.hero');
        if (hero) {
            hero.style.transform = `translateY(${scrolled * 0.3}px)`;
        }
    });
}

function initTrackEvents() {
    if (typeof TrackSDK === 'undefined') return;
    var pageName = window.location.pathname.split('/').pop() || 'index.html';
    if (document.referrer) {
        TrackSDK.report({
            module: 'site-show-referrer',
            content: pageName,
            category: TrackSDK.CATEGORY.PAGE_VIEW,
            extraData: { referrer: document.referrer }
        });
    } else {
        TrackSDK.trackPageView('site-show', pageName);
    }

    document.querySelectorAll('.project-card').forEach(function(card) {
        card.addEventListener('click', function() {
            var title = this.querySelector('.project-title, h3, h2');
            TrackSDK.trackClick('project', title ? title.textContent.trim() : 'project-card', this.id || '');
        });
    });

    document.querySelectorAll('.article-item').forEach(function(article) {
        article.addEventListener('click', function() {
            var title = this.querySelector('.article-title, h3, h2');
            TrackSDK.trackClick('article', title ? title.textContent.trim() : 'article-item', this.id || '');
        });
    });

    document.querySelectorAll('.video-card').forEach(function(card) {
        card.addEventListener('click', function() {
            var title = this.querySelector('.video-title, h3, h2');
            TrackSDK.trackClick('video', title ? title.textContent.trim() : 'video-card', this.id || '');
        });
    });

    document.querySelectorAll('a[target="_blank"]').forEach(function(link) {
        link.addEventListener('click', function() {
            var href = this.getAttribute('href') || '';
            var isGithub = href.indexOf('github.com') !== -1;
            if (isGithub) {
                TrackSDK.trackExternalLink('link-github', this.textContent.trim(), href);
            } else {
                TrackSDK.trackExternalLink('link-href', this.textContent.trim(), href);
            }
        });
    });

    document.querySelectorAll('a[href]:not([href^="#"]):not([href^="javascript"]):not([target="_blank"])').forEach(function(link) {
        link.addEventListener('click', function() {
            if (this.hostname === window.location.hostname) {
                var fromPage = window.location.pathname.split('/').pop() || 'index.html';
                var toPage = this.href.split('/').pop() || 'index.html';
                TrackSDK.trackPageRedirect('site-leave', fromPage + '->' + toPage, this.href);
            }
        });
    });
    document.querySelectorAll('.btn-get-resume').forEach(function(link) {
        link.addEventListener('click', function() {
            if (typeof TrackSDK !== 'undefined') {
                TrackSDK.trackClick('resume-click', '获取简历', this.id || '');
            }
            if (typeof ResumeModal !== 'undefined') {
                ResumeModal.open('resume-click');
            }
        });
    });
}
