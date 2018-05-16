var URL = 'http://10.21.48.11:1205';

function showEnrollTable() {
    document.getElementById("enrollTable").style.display = "block";
}

function closeEnrollTable() {
    document.getElementById("enrollTable").style.display = "none";
}

function getVal(id) {
    return document.getElementById(id).value;
}

function getRVal(name) {
    var target = document.getElementsByName(name);
    for (let i = 0; i < target.length; i++) {
        if (target[i].checked) {
            return target[i].value;
        }
    }
}

function check() {
    var StuNum = getVal("num");
    if (!(/^(3[1|2]1[6|7]00)\d{4}$/.test(StuNum))) {
        $("#num").attr('class', 'error');
    } else {
        $("#num").removeAttr('class');
        $.ajax({
            url: URL + "/recruit/find",
            type: "POST",
            data: JSON.stringify({ studentId: StuNum }),
            contentType: "application/json",
            dataType: "json",
            success: function(msg) {
                if (msg.state == 1) {
                    $.alertView('该学号已经报名过，如不是本人操作，请加群687057098联系管理员');
                }
                if (msg.state != 200 && msg.state != 1) {
                    $.alertView(msg.info);
                }
            },
            error: function(msg) {
                $.alertView("网络或程序出错!");
            }
        });
    }
}
var RegNbr = '^[\\+\\-]?\\d+\\.?\\d{0,2}';

function send() {
    var name = getVal("name");
    var num = getVal("num");
    var clas = getVal("clas");
    var phone = getVal("phone");
    var gpa = getVal("gpa");
    var failed = getRVal("failed");
    var cScore = getVal("cScore");
    var cTestScore = getVal("cTestScore");
    var enScore = getVal("enScore");
    var wish = getRVal("groupCode");
    var swap = getRVal("swap");
    if (name === '') {
        $.alertView('请先输入姓名');
    } else if (!(/^(3[1|2]1[6|7]00)\d{4}$/.test(num))) {
        $.alertView("请输入正确的学号");
    } else if (clas === '') {
        $.alertView('请输入专业班级');
    } else if (!(/^1(3|4|5|7|8)\d{9}$/.test(phone))) {
        $.alertView("为了能与你联系请输入中国手机号码😅");
    } else if (gpa == '' || cScore == '' || cTestScore == '' || enScore == '') {
        $.alertView('请填写完整各类考试的成绩');
    } else if (!(/^(?!0+(?:\.0+)?$)(?:[1-5]\d*|0)(?:\.\d{1,2})?$/.test(gpa)) || gpa > 5.0) {
        $.alertView('请检查绩点是否为正确,0~5哦');
    } else if (!(/^([1-9]\d?|100)$/.test(cScore)) || cScore < 0) {
        $.alertView('C语言成绩有误');
    } else if (!(/^([1-9]\d?|100)$/.test(enScore)) || enScore < 0) {
        $.alertView('英语成绩有误');
    } else {
        var pData = {
            name: name,
            studentId: num,
            aClass: clas,
            phone: phone,
            gpa: gpa,
            failed: failed,
            cScore: cScore,
            cTestScore: cTestScore,
            enScore: enScore,
            wish: wish,
            swap: swap
        };


        var json = {
            title: "确认提交吗?",
            msg: "请检查报名信息，每个学号只能注册一次，提交之后就无法修改",
            buttons: [{
                    title: "取消",
                    click: function() {
                    }
                },
                { title: "确认", click: function() {
                     $.ajax({
                            type: "POST",
                            url: URL + "/recruit/enroll",
                            data: JSON.stringify(pData),
                            contentType: "application/json",
                            dataType: "json",
                            success: function(msg) {
                                switch (msg.state) {
                                    case 200:
                                        $.alertView("恭喜你,报名成功！");
                                        setTimeout(function() {
                                            location.reload();
                                        }, 1500);
                                        break;
                                    case 500:
                                        $.alertView(msg.info);
                                        break;
                                    case 1:
                                        $.alertView(msg.info + "！若要修改信息加群687057098联系管理员");
                                        break;
                                    case 2:
                                        $.alertView(msg.info);
                                        break;
                                    case 3:
                                        $.alertView(msg.info);
                                        break;
                                    case 4:
                                        $.alertView(msg.info);
                                        break;
                                    case 5:
                                        $.alertView(msg.info);
                                        break;
                                    case 6:
                                        $.alertView(msg.info);
                                        break;
                                    case 7:
                                        $.alertView(msg.info);
                                        break;
                                    case 8:
                                        $.alertView(msg.info);
                                        break;
                                    case 9:
                                        $.alertView(msg.info);
                                        break;
                                    case 10:
                                        $.alertView(msg.info);
                                        break;
                                    case 11:
                                        $.alertView(msg.info);
                                        break;
                                    case 12:
                                        $.alertView(msg.info);
                                        break;
                                    case 13:
                                        $.alertView(msg.info);
                                        break;
                                    case 14:
                                        $.alertView(msg.info);
                                        break;
                                    case 15:
                                        $.alertView(msg.info);
                                        break;
                                    case 16:
                                        $.alertView(msg.info);
                                        break;
                                    case 17:
                                        $.alertView(msg.info);
                                        break;
                                    case 18:
                                        $.alertView(msg.info);
                                        break;
                                    default:
                                        $.alertView("服务器好像在开玩笑");
                                        break;
                                }
                            },
                            error: function(msg) {
                                $.alertView("网络或程序出错!");
                            }
                        });
                } }
            ]
        };
        $.alertView(json);
    }
}