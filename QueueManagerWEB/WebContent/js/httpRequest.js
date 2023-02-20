// httpRequest.js
   function createXmlHttpRequest()
	{
	   if(window.ActiveXObject){
	       return new ActiveXObject("Microsoft.XMLHTTP");
	   }else if(window.XMLHttpRequest){
	       return new XMLHttpRequest();
	   }
	}
	function startRequest(url,ele, btn)
	{
	  //document.getElementById(ele).innerHTML="<img src=\"./images/waiting.gif\">";
	  var xmlHttp=createXmlHttpRequest();
	  xmlHttp.open("GET",url,true);
	  xmlHttp.onreadystatechange=function(){
	   if(xmlHttp.readyState==4)
	    {
	       	if(xmlHttp.status==200){
		       var message=xmlHttp.responseText; 
		       if(ele!=""){
		       	  document.getElementById(ele).innerHTML=message; 
		       	  //alert(message);
		       }
	        }else{
	           alert("Error loading pagen"+ xmlHttp.status +":"+xmlHttp.statusText);
	        }
	        
	    }
	  };
	  xmlHttp.send(null);
	}
	function startRequestNoneWait(url,ele)
	{
	  var xmlHttp=createXmlHttpRequest();
	  xmlHttp.open("GET",url,true);
	  xmlHttp.onreadystatechange=function(){
	   if(xmlHttp.readyState==4)
	    {
	       	if(xmlHttp.status==200){
		       var message=xmlHttp.responseText; 
		       if(ele!=""){
		       	  document.getElementById(ele).innerHTML=message; 
		       	  //alert(message);
		       }
	        }else{
	           alert("Error loading pagen"+ xmlHttp.status +":"+xmlHttp.statusText);
	        }
	        
	    }
	  };
	  xmlHttp.send(null);
	 
	}
	function startRequestM(url)
	{
	  var message="";
	  var xmlHttp=createXmlHttpRequest();
	  xmlHttp.open("GET",url,true);
	  xmlHttp.onreadystatechange=function(){
	   if(xmlHttp.readyState==4)
	    {
	       	if(xmlHttp.status==200){
		       message=xmlHttp.responseText; 
	        }else{
	           alert("Error loading pagen"+ xmlHttp.status +":"+xmlHttp.statusText);
	        }  
	    }
	  };
	  xmlHttp.send(null);
	  return message;
	}
	
	

	