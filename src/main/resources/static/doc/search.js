let api = [];
api.push({
    alias: 'api',
    order: '1',
    desc: '登录注册',
    link: '登录注册',
    list: []
})
api[0].list.push({
    order: '1',
    methodId: '1d1b3f87eb9a4ea06d5140c0397afc31',
    desc: '登录',
});
api[0].list.push({
    order: '2',
    methodId: 'd04a7c88d9a84c0bc202ba1c9dbd68fe',
    desc: '退出',
});
api.push({
    alias: 'CaptchaController',
    order: '2',
    desc: '验证码管理',
    link: '验证码管理',
    list: []
})
api[1].list.push({
    order: '1',
    methodId: 'd580f1305454cd473c864270667aac54',
    desc: '获取验证码',
});
api.push({
    alias: 'UserController',
    order: '3',
    desc: '用户管理',
    link: '用户管理',
    list: []
})
api[2].list.push({
    order: '1',
    methodId: 'd49e8888e0f4274a90fcd465c2dbb3cd',
    desc: 'getInfo',
});
api[2].list.push({
    order: '2',
    methodId: '5dc57620cde62864b932f7c4f14957d3',
    desc: 'page',
});
api[2].list.push({
    order: '3',
    methodId: 'a12d74ada77a878655863f23b4f4dbc9',
    desc: 'detail',
});
api[2].list.push({
    order: '4',
    methodId: '987fe0f40ffc4150a1ae1392570496e8',
    desc: 'add',
});
api[2].list.push({
    order: '5',
    methodId: 'ebc4d2542c7cf9efb50ba33b0d23ce4f',
    desc: 'update',
});
api.push({
    alias: 'RoleController',
    order: '4',
    desc: '角色管理',
    link: '角色管理',
    list: []
})
api[3].list.push({
    order: '1',
    methodId: '3c2820fe7a002e923b2ee0ccb826867e',
    desc: 'page',
});
api[3].list.push({
    order: '2',
    methodId: '8ca26ed966c1b9c829756fcb315ca231',
    desc: 'detail',
});
api[3].list.push({
    order: '3',
    methodId: '9d59eb79723ea5de91bdf1f92184e7ae',
    desc: 'add',
});
api[3].list.push({
    order: '4',
    methodId: '97a7f35e574321774f9b500c9e9894c1',
    desc: 'update',
});
api[3].list.push({
    order: '5',
    methodId: '762fe7a0cb1e41788b97ebd521b84003',
    desc: 'updateDataScope',
});
api[3].list.push({
    order: '6',
    methodId: '41cfe4841edbcb1327b34a34186ef450',
    desc: 'updateState',
});
api[3].list.push({
    order: '7',
    methodId: '4e2139db23f5af438d450245f0a4de3d',
    desc: 'delete',
});
api.push({
    alias: 'DeptController',
    order: '5',
    desc: '部门管理',
    link: '部门管理',
    list: []
})
api[4].list.push({
    order: '1',
    methodId: '533830910f19dc776b303602cdd5d82a',
    desc: 'list',
});
api[4].list.push({
    order: '2',
    methodId: '9b865b92bb0d92dffb1bab7befbb9222',
    desc: 'detail',
});
api[4].list.push({
    order: '3',
    methodId: '6b9976402c7f45a573010719c6299a75',
    desc: 'add',
});
api[4].list.push({
    order: '4',
    methodId: '7268ee8a92128e006474a917826e979d',
    desc: 'update',
});
api[4].list.push({
    order: '5',
    methodId: '652a41c75c6eae6020e8d3bb6bae1813',
    desc: 'delete',
});
api[4].list.push({
    order: '6',
    methodId: '354927ab311fbed2ff325eec644fd92f',
    desc: 'userDeptTree',
});
api[4].list.push({
    order: '7',
    methodId: '8a3e27b1fce73fdbc158edecc351b9e1',
    desc: 'roleDeptTree',
});
api.push({
    alias: 'MenuController',
    order: '6',
    desc: '菜单管理',
    link: '菜单管理',
    list: []
})
api[5].list.push({
    order: '1',
    methodId: '0b26860a8dd39ea7d13406264a96152a',
    desc: 'getRouters',
});
api[5].list.push({
    order: '2',
    methodId: 'ca871dbebcf19f7b86f043a584b8fe0a',
    desc: 'list',
});
api[5].list.push({
    order: '3',
    methodId: '8fbbff877a267b14924744f36d45ad37',
    desc: 'detail',
});
api[5].list.push({
    order: '4',
    methodId: 'a86ec5bb2d8f12650fc91f9d8020fe88',
    desc: 'add',
});
api[5].list.push({
    order: '5',
    methodId: '4bdc9fb193a9bcc874dbf21fe7519a8f',
    desc: 'update',
});
api[5].list.push({
    order: '6',
    methodId: 'f3ddf99961e35556879ce4c68725cd3a',
    desc: 'delete',
});
api[5].list.push({
    order: '7',
    methodId: '9a98b9790ff9567bcfa1b74c20df5f0f',
    desc: 'userMenuTree',
});
api[5].list.push({
    order: '8',
    methodId: '5f588b9702a1949b0f644df7a36877ff',
    desc: 'roleMenuTree',
});
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code === 13) {
        const search = document.getElementById('search');
        const searchValue = search.value;
        let searchArr = [];
        for (let i = 0; i < api.length; i++) {
            let apiData = api[i];
            const desc = apiData.desc;
            if (desc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                searchArr.push({
                    order: apiData.order,
                    desc: apiData.desc,
                    link: apiData.link,
                    alias: apiData.alias,
                    list: apiData.list
                });
            } else {
                let methodList = apiData.list || [];
                let methodListTemp = [];
                for (let j = 0; j < methodList.length; j++) {
                    const methodData = methodList[j];
                    const methodDesc = methodData.desc;
                    if (methodDesc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                        methodListTemp.push(methodData);
                        break;
                    }
                }
                if (methodListTemp.length > 0) {
                    const data = {
                        order: apiData.order,
                        desc: apiData.desc,
                        alias: apiData.alias,
                        link: apiData.link,
                        list: methodListTemp
                    };
                    searchArr.push(data);
                }
            }
        }
        let html;
        if (searchValue === '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchArr,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            let $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiData, liClass, display) {
    let html = "";
    if (apiData.length > 0) {
         for (let j = 0; j < apiData.length; j++) {
            html += '<li class="'+liClass+'">';
            html += '<a class="dd" href="#' + apiData[j].alias + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
            html += '<ul class="sectlevel2" style="'+display+'">';
            let doc = apiData[j].list;
            for (let m = 0; m < doc.length; m++) {
                html += '<li><a href="#' + doc[m].methodId + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + doc[m].desc + '</a> </li>';
            }
            html += '</ul>';
            html += '</li>';
        }
    }
    return html;
}