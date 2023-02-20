      // Global Variable for XmlHttp Request Object   
      var xmlhttp
      // Timer Variables
      var c = 0
      var t
       /* A function which calls a servlet  named AjaxServlet to get XmlData using XmlHttpObject */     
        function refreshCombo(txt){
            xmlhttp = null
 
            // code for initializing XmlHttpRequest Object On Browsers like  Mozilla, etc.
 
            if (window.XMLHttpRequest){
  
                 xmlhttp = new XMLHttpRequest()
                   
            }
 
            // code for initializing XmlHttpRequest Object On Browsers like IE
 
           else if (window.ActiveXObject) {
  
               xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")
           
           }
        
           if (xmlhttp != null){
  
              // Setting the Servlet url to get XmlData
               url = "SecondCB_Servlet?req="+txt;
               
               // Course of Action That Should be Made if their is a change in XmlHttpRequest Object ReadyState NOTE : it is 4 when it has got request from CGI
               xmlhttp.onreadystatechange = getResponseAction;
  
               // Open the Request by passing Type of Request & CGI URL 
               xmlhttp.open("GET",url,true);
  
               // Sending URL Encoded Data
               xmlhttp.send(null);
               
           }
           else{
  
             // Only Broswers like IE 5.0,Mozilla & all other browser which support XML data Supports AJAX Technology
             // In the Below case it looks as if the browser is not compatiable
              alert("Your browser does not support XMLHTTP.")
              
           }
           
      }
        
      
      /* Used for verifing right ReadyState & Status of XmlHttpRequest Object returns true if it is verified */
      function verifyReadyState(obj){
  
         // As Said above if XmlHttp.ReadyState == 4 then the Page Has got Response from WebServer
          if(obj.readyState == 4){
                         
           // Similarly if XmlHttp.status == 200 it means that we have got a Valid response from the WebServer
            if(obj.status == 200){                
                
                return true
             }
             else{
      
                alert("Problem retrieving XML data")
             }
             
          }
          
      }
      
      
      /* Action that has to take place after getting reponse */
      function getResponseAction(){
         
          // Verifying State & Status
          if(verifyReadyState(xmlhttp) == true){
                          
              // Building a DOM parser from Response Object
              var response = xmlhttp.responseXML.documentElement
              
              // Deleting all the Present Elements in the Drop-Down Box
              drRemove()       
              
              // Checking for the Root Node Tag
              var x = response.getElementsByTagName("option")
              var val
              var tex
              var optn
              
              for(var i = 0;i < x.length; i++){
                 
                 optn = document.createElement("OPTION")
                 var er
                 
                 // Checking for the tag which holds the value of the Drop-Down combo element
                 val = x[i].getElementsByTagName("val")
                 {
                     
                     try{
                       
                        // Assigning the value to a Drop-Down Set Element
                        optn.value = val[0].firstChild.data
                        
                     } catch(er){
                                             
                     }
                     
                 }
                 
                 // Checking for the tag which holds the Text of the Drop-Down combo element
                 tex = x[i].getElementsByTagName("text")
                 {
                 
                 
                     try{
                       
                        // Assigning the Text to a Drop-Down Set Element
                        optn.text = tex[0].firstChild.data
                        
                     } catch(er){
                                             
                     }
                 
                 }
                 
                 // Adding the Set Element to the Drop-Down
                 document.CompanyForm.state.options.add(optn)
				
                 
              }
              
              
         }     
      
         
      }
        
      
      /* Function removes all the elements in the Drop-Down */
      function drRemove(){
                     
        document.CompanyForm.state.options.length = 0;           
        
      } 
