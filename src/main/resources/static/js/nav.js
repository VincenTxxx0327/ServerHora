(function() {
    'use strict';

    function initNavigation() {
        highlightCurrentPage();
        initMobileMenu();
    }

    function highlightCurrentPage() {
        const currentPath = window.location.pathname;
        const fileName = currentPath.split('/').pop() || 'index.html';
        
        const navLinks = document.querySelectorAll('.site-nav__link');
        
        navLinks.forEach(link => {
            const href = link.getAttribute('href');
            link.classList.remove('site-nav__link--active');
            
            if (href === fileName || 
                (fileName === '' && href === 'index.html') ||
                (fileName === 'index.html' && href === 'index.html')) {
                link.classList.add('site-nav__link--active');
            }
        });
    }

    function initMobileMenu() {
        const menuButton = document.querySelector('.site-nav__mobile-menu');
        const dropdown = document.querySelector('.site-nav__mobile-dropdown');
        
        if (!menuButton || !dropdown) return;

        menuButton.addEventListener('click', function() {
            this.classList.toggle('site-nav__mobile-menu--open');
            dropdown.classList.toggle('site-nav__mobile-dropdown--open');
        });

        document.addEventListener('click', function(e) {
            if (!menuButton.contains(e.target) && !dropdown.contains(e.target)) {
                menuButton.classList.remove('site-nav__mobile-menu--open');
                dropdown.classList.remove('site-nav__mobile-dropdown--open');
            }
        });

        const dropdownLinks = dropdown.querySelectorAll('.site-nav__link');
        dropdownLinks.forEach(link => {
            link.addEventListener('click', function() {
                menuButton.classList.remove('site-nav__mobile-menu--open');
                dropdown.classList.remove('site-nav__mobile-dropdown--open');
            });
        });
    }

    function createNavigation(config) {
        const nav = document.createElement('nav');
        nav.className = 'site-nav';
        
        const currentPage = window.location.pathname.split('/').pop() || 'index.html';
        
        nav.innerHTML = `
            <a href="index.html" class="site-nav__logo">THE EDITORIALIST</a>
            
            <div class="site-nav__links">
                <a class="site-nav__link ${currentPage === 'index.html' ? 'site-nav__link--active' : ''}" href="index.html">Home</a>
                <a class="site-nav__link ${currentPage === 'about.html' ? 'site-nav__link--active' : ''}" href="about.html">About Me</a>
                <a class="site-nav__link ${currentPage === 'articles.html' ? 'site-nav__link--active' : ''}" href="articles.html">Articles</a>
                <a class="site-nav__link ${currentPage === 'projects.html' ? 'site-nav__link--active' : ''}" href="projects.html">Projects</a>
                <a class="site-nav__link" href="${config.githubUrl || 'https://github.com/VincenTxxx0327'}" target="_blank">GitHub</a>
            </div>
            
            <button class="site-nav__cta">${config.ctaText || 'Hire Me'}</button>
            
            <div class="site-nav__mobile-menu">
                <span></span>
                <span></span>
                <span></span>
            </div>
            
            <div class="site-nav__mobile-dropdown">
                <a class="site-nav__link ${currentPage === 'index.html' ? 'site-nav__link--active' : ''}" href="index.html">Home</a>
                <a class="site-nav__link ${currentPage === 'about.html' ? 'site-nav__link--active' : ''}" href="about.html">About Me</a>
                <a class="site-nav__link ${currentPage === 'articles.html' ? 'site-nav__link--active' : ''}" href="articles.html">Articles</a>
                <a class="site-nav__link ${currentPage === 'projects.html' ? 'site-nav__link--active' : ''}" href="projects.html">Projects</a>
                <a class="site-nav__link" href="${config.githubUrl || 'https://github.com/VincenTxxx0327'}" target="_blank">GitHub</a>
            </div>
        `;
        
        return nav;
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initNavigation);
    } else {
        initNavigation();
    }

    window.SiteNav = {
        init: initNavigation,
        highlightCurrentPage: highlightCurrentPage,
        createNavigation: createNavigation
    };
})();
