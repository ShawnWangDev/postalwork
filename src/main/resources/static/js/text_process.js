function switchText(){
    let _originalText = document.getElementById("originalText");
    let _outputText = document.getElementById("outputText");
    let tmp=_originalText.value;
    _originalText.value=_outputText.value;
    _outputText.value=tmp;
}

function getInputTextLineArray(){
    let _originalText = document.getElementById("originalText");
    let arr = _originalText.value.split("\n");
    return arr;
}

function getInputTextNoEmptyLineArray(){
    let arr=getInputTextLineArray();
    let noEmptyLineArr=arr.filter(item => item!="");
    return noEmptyLineArr;
}

// for output-textarea
function arrayToRowedString(arr){
    let outputString="";
    for(let item of arr){
        outputString = outputString+item+"\n";
    }
    return outputString;
}

function contain(){
    let originalTextArray=getInputTextNoEmptyLineArray();
    let target = document.getElementById("containStrTextField").value.trim();
    let targetArr=originalTextArray.filter(item=>item.indexOf(target)!=-1);
    let _outputText = document.getElementById("outputText");
    _outputText.value=arrayToRowedString(targetArr);
}

function deleteContainPart(){
    let result=""
    let originalTextArray=getInputTextNoEmptyLineArray();
    let keyword = document.getElementById("containStrTextField").value.trim();
    for(let line of originalTextArray){
        if(line.indexOf(keyword)!=-1){
            result+=line.replace(keyword,'').trim()+"\n";
        }
    }
    let _outputText = document.getElementById("outputText");
    _outputText.value=result;
}

function substring(){
    let originalTextArray=getInputTextLineArray();
    let srcText=document.getElementById("substringSrcText").value;
    let targetText=document.getElementById("substringTargetText").value;
    for(let i=0;i<originalTextArray.length;i++){
        originalTextArray[i]=originalTextArray[i].replace(srcText,targetText);
    }
    let outputText=arrayToRowedString(originalTextArray);
    let _outputText = document.getElementById("outputText");
    _outputText.value=outputText;
}


function lineStepExtract() {
    let originalTextArray = getInputTextLineArray();
    let _startRow = document.getElementById("startRow");
    let startRow = parseInt(_startRow.value)-1;
    let _stepLength = document.getElementById("stepLength");
    let stepLength = parseInt(_stepLength.value);
    let resultString = "";
    for (let i = startRow; i < originalTextArray.length; i += stepLength) {
        resultString += originalTextArray[i].trim() + "\n";
    }
    let _outputText = document.getElementById("outputText");
    _outputText.value = resultString;
}

function regexExtract() {
    let _regexString = document.getElementById("regexString");
    let originalTextArray = getInputTextNoEmptyLineArray();
    let regex=new RegExp(_regexString.value);
    let resultArray=new Array();
    for(let line of originalTextArray){
        if(line.match(regex)){
            resultArray.push(line.trim());
        }
    }
    let resultString="";
    for(let result of resultArray){
        let line=result+"\n";
        resultString+=line;
    }
    let _outputText = document.getElementById("outputText");
    _outputText.value=resultString;
}