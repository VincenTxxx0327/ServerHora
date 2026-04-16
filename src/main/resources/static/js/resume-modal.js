(function() {
    'use strict';

    var RESUME_DOWNLOAD_URL = '/spba-api/files/resume.zip';
    var currentSource = '';

    var channelOptions = [
        { value: 'BOSS直聘', label: 'BOSS直聘' },
        { value: '拉钩', label: '拉钩' },
        { value: '智联招聘', label: '智联招聘' },
        { value: '脉脉', label: '脉脉' },
        { value: '其他', label: '其他' }
    ];

    function createModalHtml() {
        var optionsHtml = '';
        for (var i = 0; i < channelOptions.length; i++) {
            optionsHtml += '<option value="' + channelOptions[i].value + '">' + channelOptions[i].label + '</option>';
        }

        return '<div id="resume-modal-overlay" class="fixed inset-0 z-[9999] flex items-center justify-center p-4" style="display:none;">' +
            '<div class="absolute inset-0 bg-black/60" id="resume-modal-backdrop"></div>' +
            '<div class="relative bg-white border-4 border-black shadow-[8px_8px_0px_0px_rgba(0,0,0,1)] w-full max-w-md p-0" id="resume-modal-content">' +
                '<div class="flex items-center justify-between bg-on-background text-background px-6 py-3 border-b-4 border-black">' +
                    '<h3 class="font-mono font-bold text-sm uppercase tracking-widest">获取简历</h3>' +
                    '<button id="resume-modal-close" class="w-8 h-8 flex items-center justify-center hover:bg-white/20 transition-colors font-mono font-bold text-lg leading-none">&times;</button>' +
                '</div>' +
                '<div class="px-6 py-6 space-y-5">' +
                    '<div>' +
                        '<label class="block font-mono text-xs font-bold uppercase tracking-widest mb-2 text-on-surface-variant">请选择来源渠道</label>' +
                        '<select id="resume-channel-select" class="w-full border-2 border-black px-4 py-3 font-mono text-sm bg-white focus:outline-none focus:border-primary-container shadow-[3px_3px_0px_0px_rgba(0,0,0,1)]">' +
                            optionsHtml +
                        '</select>' +
                    '</div>' +
                    '<div id="resume-other-wrapper" style="display:none;">' +
                        '<label class="block font-mono text-xs font-bold uppercase tracking-widest mb-2 text-on-surface-variant">请填写渠道名称</label>' +
                        '<input id="resume-other-input" type="text" maxlength="6" placeholder="最多6个字符" class="w-full border-2 border-black px-4 py-3 font-mono text-sm bg-white focus:outline-none focus:border-primary-container shadow-[3px_3px_0px_0px_rgba(0,0,0,1)]" />' +
                    '</div>' +
                '</div>' +
                '<div class="flex border-t-2 border-black">' +
                    '<button id="resume-modal-cancel" class="flex-1 py-3 font-mono font-bold text-sm uppercase tracking-widest border-r-2 border-black hover:bg-surface-container transition-colors">取消</button>' +
                    '<button id="resume-modal-confirm" class="flex-1 py-3 font-mono font-bold text-sm uppercase tracking-widest bg-primary-container hover:translate-x-[1px] hover:translate-y-[1px] hover:shadow-none shadow-[3px_3px_0px_0px_rgba(0,0,0,1)] transition-all border-l-2 border-black">获取简历</button>' +
                '</div>' +
            '</div>' +
        '</div>';
    }

    function getModal() {
        return document.getElementById('resume-modal-overlay');
    }

    function createModal() {
        var existing = getModal();
        if (existing) return existing;

        var wrapper = document.createElement('div');
        wrapper.innerHTML = createModalHtml();
        var modal = wrapper.firstChild;
        document.body.appendChild(modal);

        var selectEl = document.getElementById('resume-channel-select');
        var otherWrapper = document.getElementById('resume-other-wrapper');
        var otherInput = document.getElementById('resume-other-input');

        selectEl.addEventListener('change', function() {
            if (this.value === '其他') {
                otherWrapper.style.display = 'block';
                otherInput.focus();
            } else {
                otherWrapper.style.display = 'none';
                otherInput.value = '';
            }
        });

        document.getElementById('resume-modal-backdrop').addEventListener('click', close);
        document.getElementById('resume-modal-close').addEventListener('click', close);
        document.getElementById('resume-modal-cancel').addEventListener('click', close);
        document.getElementById('resume-modal-confirm').addEventListener('click', handleConfirm);

        document.addEventListener('keydown', function(e) {
            if (e.key === 'Escape' && getModal() && getModal().style.display !== 'none') {
                close();
            }
        });

        return modal;
    }

    function open(source) {
        currentSource = source || 'resume-click';
        var modal = createModal();

        var selectEl = document.getElementById('resume-channel-select');
        var otherWrapper = document.getElementById('resume-other-wrapper');
        var otherInput = document.getElementById('resume-other-input');

        selectEl.value = 'BOSS直聘';
        otherWrapper.style.display = 'none';
        otherInput.value = '';

        modal.style.display = 'flex';
        document.body.style.overflow = 'hidden';
    }

    function close() {
        var modal = getModal();
        if (modal) {
            modal.style.display = 'none';
            document.body.style.overflow = '';
        }
    }

    function getChannelContent() {
        var selectEl = document.getElementById('resume-channel-select');
        var otherInput = document.getElementById('resume-other-input');
        var channel = selectEl.value;

        if (channel === '其他') {
            var customText = (otherInput.value || '').trim();
            if (customText === '') {
                return '其他'
            } else {
                return '其他：' + customText;
            }
        }

        return channel;
    }

    function handleConfirm() {
        var content = getChannelContent();
        var module = currentSource;

        if (typeof TrackSDK !== 'undefined') {
            TrackSDK.report({
                module: module,
                content: content,
                category: 5
            });
        }

        close();

        downloadResume();
    }

    function downloadResume() {
        var a = document.createElement('a');
        a.href = RESUME_DOWNLOAD_URL;
        a.download = 'resume.zip';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
    }

    window.ResumeModal = {
        open: open,
        close: close
    };
})();
