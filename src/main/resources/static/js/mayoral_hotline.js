$(".expressBrandSelect").each(function(){
    $(this).change(function(){
        let id=$(this).parents("tr").find("input").val();
        let expressBrand=new Object();
        expressBrand.id=$(this).val();
        let sendData={
            id:id,
            expressBrand:expressBrand,
        };
        if(expressBrand.id!=0){
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mayoral_hotline_labeled/update",
                data: JSON.stringify(sendData),
                cache: false,
                success: function(result) {
                    location.reload(true);
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