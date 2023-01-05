var expressBrandImages = [
    { id: 1, name: 'other' },
    { id: 2, name: 'STO' },
    { id: 3, name: 'YT' },
    { id: 4, name: 'ZTO' },
    { id: 5, name: 'YunDa' },
    { id: 6, name: 'SF' },
    { id: 7, name: 'FengWang' },
    { id: 8, name: 'EMS' },
    { id: 9, name: 'CaiNiao' },
    { id: 10, name: 'JiTu' },
    { id: 11, name: 'Deppon' },
    { id: 12, name: 'DanNiao' },
    { id: 13, name: 'JD' },
    { id: 14, name: 'HiveBox' },
    { id: 15, name: 'other' },
    { id: 16, name: 'multiple' },
]

function getExpressBrandImagePath(id) {
    let expressBrandName = expressBrandImages.filter(ele => ele.id == id)[0].name;
    let path = "/static/image/express_brand/";
    let imageExtension = '.jpg';
    return path + expressBrandName + imageExtension;
}

$(".expressBrandSelect").each(function(){
    $(this).change(function(){
        let id=$(this).parents("tr").find("input").val();
        let expressBrandId=$(this).val();
        let expressBrand=new Object();
        expressBrand.id=expressBrandId;
        let sendData={
            id:id,
            expressBrand:expressBrand,
        };
        let image=$(this).parents('td').find('img');
        if(expressBrand.id!=0){
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mayoral_hotline_labeled/update",
                data: JSON.stringify(sendData),
                cache: false,
                success: function(result) {
                    image.attr('src',getExpressBrandImagePath(expressBrandId));
//                    location.reload(true);
                },
                error: function(err) {
                    console.log("update error.")
                }
            });
        }
    });
});

$(".conditionSelect").each(function(){
    $(this).change(function(){
        let id=$(this).parents("tr").find("input:hidden").val();
        let issueCondition=new Object();
        issueCondition.id=$(this).val();
        let tableTrNode=$(this).parent().parent("tr");
        let sendData={
            id:id,
            issueCondition:issueCondition,
        };
        if(issueCondition.id!=0){
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mayoral_hotline_labeled/update",
                data: JSON.stringify(sendData),
                cache: false,
                success: function(result) {
                    tableTrNode.attr('class','issue_condition_id_'+issueCondition.id);
                },
                error: function(err) {
                console.log("update error.")
                }
            });
        }
    });
});

$(".typeSelect").each(function(){
    $(this).change(function(){
        let id=$(this).parents("tr").find("input").val();
        let issueType=new Object();
        issueType.id=$(this).val();
        let sendData={
            id:id,
            issueType:issueType,
        };
        if(issueType.id!=0){
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mayoral_hotline_labeled/update",
                data: JSON.stringify(sendData),
                cache: false,
                success: function(result) {
                    // location.reload(true);
                },
                error: function(err) {
                    alert("update error.")
                }
            });
        }
    });
});