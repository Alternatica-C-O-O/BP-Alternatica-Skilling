
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
    'index.csr.html': {size: 48440, hash: 'd6b26ae25179105ddaa1d8947a03b16cf20bb91b107e89a2d52e2d7dc986c051', text: () => import('./assets-chunks/index_csr_html.mjs').then(m => m.default)},
    'index.server.html': {size: 1573, hash: '3001a4883aa8221a007be95d5a1c2a21202b7edfda35bd0dcddd03b57844eab1', text: () => import('./assets-chunks/index_server_html.mjs').then(m => m.default)},
    'index.html': {size: 49780, hash: '0f094f26fb41d5adeb17f3cd6cd543acdabeab9d6e836f4aa07eafb01100fcc2', text: () => import('./assets-chunks/index_html.mjs').then(m => m.default)},
    'styles-EP5J2MJL.css': {size: 90124, hash: 'chtdJxUMriE', text: () => import('./assets-chunks/styles-EP5J2MJL_css.mjs').then(m => m.default)}
  },
};
