if(!window.$id){var $id=function(A){return document.getElementById(A);};}var hi5=hi5||{};hi5.Updater=function(B,D,A){var C,E;if(window.ActiveXObject){C=new ActiveXObject("Microsoft.XMLHTTP");}else{if(window.XMLHttpRequest){C=new XMLHttpRequest();}}for(opt in A){switch(A){case"onComplete":onComple=A[opt];break;}}C.open("get",D,true);C.onreadystatechange=function(){if(C.readyState==4){$id(B).innerHTML=C.responseText;if(E){E(C);}}};C.send(null);};hi5.Lightbox=function(H,A,C){var B=$id("black-overlay"),G=$id("iframe-overlay"),F=$id("dialog-overlay");$id("dialog-title").innerHTML=H;$id("dialog-body").innerHTML=A;for(opt in C){switch(opt){case"acceptText":$id("dialog-accept").innerHTML=C[opt];break;case"onAccept":var E=$id("dialog-accept");E.callback=C[opt];E.onclick=function(){this.callback();F.style.display=G.style.display=B.style.display="none";};break;case"cancelText":$id("dialog-cancel").innerHTML=C[opt];break;case"onCancel":var D=$id("dialog-cancel");D.callback=C[opt];D.onclick=function(){this.callback();F.style.display=G.style.display=B.style.display="none";};break;case"styleClass":F.className=G.className=C[opt];break;}}setTimeout(function(){var I=0;if(document.documentElement.scrollTop!=undefined){I=document.documentElement.scrollTop;}else{if(window.scrollY!=undefined){I=window.scrollY;}}F.style.display=G.style.display=B.style.display="block";F.style.marginTop=G.style.marginTop=-F.offsetHeight/2+I+"px";G.style.width=F.offsetWidth+"px";G.style.height=F.offsetHeight+"px";},0);};hi5.Lightbox.prototype.close=function(){var A=$id("black-overlay"),C=$id("iframe-overlay"),B=$id("dialog-overlay");B.style.display=C.style.display=A.style.display="none";};