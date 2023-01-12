var getFirstArrayNotInSecondArray=function(a1,a2){
 let set2=new Set(a2);
 let resultArray=new Array();
 for(let ele of a1){
  if(!set2.has(ele)){
    resultArray.push(ele);
  }
 }
 return resultArray;
}

$("#compareCodeBtn").click(function(){
let inputCodeArray=new Array();
let inputCodes = $("#inputCodes").val().split("\n");
let regex=new RegExp("^\\d{14}$");
for(let code of inputCodes){
  if(code.trim().match(regex)){
    inputCodeArray.push(code);
  }
}
let tableCellCodeArray = new Array();
$('.hotlineCode').each(function(){
  let code=$(this).text();
  tableCellCodeArray.push(code);
});
$("#set1").children("p").remove();
$("#set2").children("p").remove();
for(let code of getFirstArrayNotInSecondArray(inputCodeArray,tableCellCodeArray)){
  $("#set1").append($("<p>"+code+"</p>"));
}
for(let code of getFirstArrayNotInSecondArray(tableCellCodeArray,inputCodeArray)){
  $("#set2").append($("<p>"+code+"</p>"));
}
});