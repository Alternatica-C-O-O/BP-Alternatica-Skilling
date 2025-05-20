
export default {
  bootstrap: () => import('./main.server.mjs').then(m => m.default),
  inlineCriticalCss: true,
  baseHref: '/',
  locale: undefined,
  routes: [
  {
    "renderMode": 2,
    "route": "/"
  }
],
  entryPointToBrowserMapping: undefined,
  assets: {
    'index.csr.html': {size: 2192, hash: '1a0a64672f1f8ab808c2e5afc634033ac0b68a115be11c408439fb15d4f2c140', text: () => import('./assets-chunks/index_csr_html.mjs').then(m => m.default)},
    'index.server.html': {size: 1005, hash: '253534f6f25450902476083b6c9dd1b0af554a19d45c0f1ecf07de548c4d67e4', text: () => import('./assets-chunks/index_server_html.mjs').then(m => m.default)},
    'index.html': {size: 22687, hash: '84100118625093717c5db84c7bdbdfa3bfe7d649e6be0a7c87ac1b09eefdcda8', text: () => import('./assets-chunks/index_html.mjs').then(m => m.default)},
    'styles-ZDLR6C6W.css': {size: 5290, hash: 'T3beCwnVmzQ', text: () => import('./assets-chunks/styles-ZDLR6C6W_css.mjs').then(m => m.default)}
  },
};
