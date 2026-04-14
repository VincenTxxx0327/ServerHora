(function() {
    'use strict';

    var TRACK_API = '/spba-api/track/event';
    var TRACK_BATCH_API = '/spba-api/track/events';

    var CATEGORY = {
        CLICK: 1,
        PAGE_VIEW: 2,
        PAGE_REDIRECT: 3,
        EXTERNAL_LINK: 4,
        TRIGGER_ACTION: 5
    };

    var _sessionId = null;
    var _eventQueue = [];
    var _flushTimer = null;
    var FLUSH_INTERVAL = 5000;
    var MAX_QUEUE_SIZE = 10;

    function getSessionId() {
        if (_sessionId) return _sessionId;
        var key = 'track_sid';
        _sessionId = sessionStorage.getItem(key);
        if (!_sessionId) {
            _sessionId = 'sid_' + Date.now() + '_' + Math.random().toString(36).substr(2, 9);
            sessionStorage.setItem(key, _sessionId);
        }
        return _sessionId;
    }

    function buildEvent(options) {
        return {
            module: options.module || 'default',
            content: options.content || '',
            category: options.category || CATEGORY.CLICK,
            triggerTime: options.triggerTime || new Date().toISOString(),
            pageUrl: options.pageUrl || window.location.href,
            elementId: options.elementId || '',
            sessionId: getSessionId(),
            extraData: options.extraData ? JSON.stringify(options.extraData) : ''
        };
    }

    function sendEvent(event) {
        fetch(TRACK_API, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(event),
            keepalive: true
        }).catch(function() {});
    }

    function flushQueue() {
        if (_eventQueue.length === 0) return;
        var events = _eventQueue.splice(0, _eventQueue.length);
        fetch(TRACK_BATCH_API, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(events),
            keepalive: true
        }).catch(function() {});
    }

    function enqueueEvent(event) {
        _eventQueue.push(event);
        if (_eventQueue.length >= MAX_QUEUE_SIZE) {
            flushQueue();
        } else if (!_flushTimer) {
            _flushTimer = setTimeout(function() {
                _flushTimer = null;
                flushQueue();
            }, FLUSH_INTERVAL);
        }
    }

    window.TrackSDK = {
        CATEGORY: CATEGORY,

        report: function(options) {
            var event = buildEvent(options);
            sendEvent(event);
        },

        reportBatch: function(options) {
            var event = buildEvent(options);
            enqueueEvent(event);
        },

        flush: function() {
            if (_flushTimer) {
                clearTimeout(_flushTimer);
                _flushTimer = null;
            }
            flushQueue();
        },

        trackClick: function(module, content, elementId, extra) {
            this.report({
                module: module,
                content: content,
                category: CATEGORY.CLICK,
                elementId: elementId || '',
                extraData: extra || null
            });
        },

        trackPageView: function(module, content) {
            this.report({
                module: module,
                content: content || document.title,
                category: CATEGORY.PAGE_VIEW
            });
        },

        trackPageRedirect: function(module, content, targetUrl) {
            this.report({
                module: module,
                content: content,
                category: CATEGORY.PAGE_REDIRECT,
                extraData: targetUrl ? { targetUrl: targetUrl } : null
            });
        },

        trackExternalLink: function(module, content, linkUrl) {
            this.report({
                module: module,
                content: content,
                category: CATEGORY.EXTERNAL_LINK,
                extraData: linkUrl ? { linkUrl: linkUrl } : null
            });
        },

        trackAction: function(module, content, extra) {
            this.report({
                module: module,
                content: content,
                category: CATEGORY.TRIGGER_ACTION,
                extraData: extra || null
            });
        }
    };

    window.addEventListener('beforeunload', function() {
        TrackSDK.flush();
    });
})();
