# Basic App to inspect presentation of Klarna checkout snippet

You will need to create you own file snippet.ts with a html snippet recieved from backend.
Place it in the root directory.

It should look like something like this:


```javascript
export const snippet = `
<div id="klarna-checkout-container" style="overflow-x: hidden;">
  <div id="klarna-unsupported-page">
  <style type="text/css">
  @-webkit-keyframes klarnaFadeIn{from{opacity:0}to{opacity:1}}@-moz-keyframes klarnaFadeIn{from{opacity:0}to{opacity:1}}@keyframes klarnaFadeIn{from{opacity:0}to{opacity:1}}#klarna-unsupported-page{opacity:0;opacity:1\9;-webkit-animation:klarnaFadeIn ease-in 1;-moz-animation:klarnaFadeIn ease-in 1;animation:klarnaFadeIn ease-in 1;-webkit-animation-fill-mode:forwards;-moz-animation-fill-mode:forwards;animation-fill-mode:forwards;-webkit-animation-duration:.1s;-moz-animation-duration:.1s;animation-duration:.1s;-webkit-animation-delay:5s;-moz-animation-delay:5s;animation-delay:5s;text-align:center;padding-top:64px}#klarna-unsupported-page .heading{font-family:Source Sans Pro,Helvetica,Arial,sans-serif;line-height:48px;font-weight:200;color:#303030;font-size:42px;margin:24px 0}#klarna-unsupported-page .subheading{font-family:Source Sans Pro,Helvetica,Arial,sans-serif;line-height:28px;font-weight:400;color:rgba(0,0,0,.7);font-size:19px;max-width:560px;margin:10px auto}#klarna-unsupported-page .subheading a{text-decoration:none;background-color:transparent;border:0;color:rgba(0,0,0,.7);font-weight:600}
  </style>
  <h1 class="heading">Oops.</h1>
    <p class="subheading">It looks like an important part of the checkout experience failed to load and we are unable to offer you a way to pay right now.</p>
    <p class="subheading">Please refresh the page to try again. If this isn't the first time you've seen this message then there may be a more permanent error and you should contact customer service at Klarna.com.</p>
  </div>
  <script type="text/javascript">
  /* <![CDATA[ */
  (function(w,k,i,d,n,c,l){
    w[k]=w[k]||function(){(w[k].q=w[k].q||[]).push(arguments)};
    l=w[k].config={
      container:w.document.getElementById(i),
      ORDER_URL:'YOUR_ORDER',
      AUTH_HEADER:'KlarnaCheckout YOUR_HEADER',
      LOCALE:'sv-SE',
      ORDER_STATUS:'checkout_incomplete',
      MERCHANT_TAC_URI:'http://www.wayke.se?terms',
      MERCHANT_NAME:'Wayke AB',
      HASHED_MERCHANT_ID:'YOUR_MERCHANT_ID',
      GUI_OPTIONS:[],
      ALLOW_SEPARATE_SHIPPING_ADDRESS:false,
      PURCHASE_COUNTRY:'swe',
      PURCHASE_CURRENCY:'sek',
      TESTDRIVE:true,
      CHECKOUT_DOMAIN:'https://checkout-eu.playground.klarna.com',
      BOOTSTRAP_SRC:'https://x.klarnacdn.net/kcoc/190703-cf1fd49/checkout.bootstrap.js',
      NATIONAL_IDENTIFICATION_NUMBER_MANDATORY:true,
      PHONE_MANDATORY:false,
      PACKSTATION_ENABLED:false,
      CLIENT_EVENT_HOST:'https://evt.playground.klarna.com',
      C2_ENABLED:true
    };
    n=d.createElement('script');
    c=d.getElementById(i);
    n.async=!0;
    n.src=l.BOOTSTRAP_SRC;
    c.appendChild(n);
    try{
      ((w.Image && (new w.Image))||(d.createElement && d.createElement('img'))||{}).src =
        l.CLIENT_EVENT_HOST + '/v1/checkout/snippet/load' +
        '?sid=' + l.ORDER_URL.split('/').slice(-1) +
        '&order_status=' + w.encodeURIComponent(l.ORDER_STATUS) +
        '&timestamp=' + (new Date).getTime();
    }catch(e){}
  })(this,'_klarnaCheckout','klarna-checkout-container',document);
  /* ]]> */
  </script>
  <noscript>
Please <a href="http://enable-javascript.com">enable JavaScript</a>.
  </noscript>
</div>`;

```