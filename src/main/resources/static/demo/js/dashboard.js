/**
 * Created by an on 2017/9/4.
 */
$(document).ready(function () {
    // 在这里写你的代码开始...
    //li 选中变色 开始
    var $left_a_list = $('div[class="second-level-left-div"] ul li a');
    $left_a_list.each(
        function () {
            $(this).click(
                function () {
                    $parent_li = $(this).parent();
                    $parent_li_siblings = $parent_li.siblings();
                    if ($(this).hasClass("a_point_choice")) {
                        var $a_siblings = $parent_li_siblings.find("a");
                        $a_siblings.each(
                            function () {
                                if (!$(this).hasClass("a_point_choice_other")) {
                                    var child_i = $(this).find('i');
                                    var child_div = $(this).find(".li-second-child-div");
                                    $(this).addClass("a_point_choice_other");
                                    $(child_i).addClass("a_point_choice_other");
                                    $(child_div).addClass("a_point_choice_other");
                                    $(this).parent().addClass("li_point_choice_other")
                                }
                            }
                        );
                    }
                    else {
                        var child_i = $(this).find('i');
                        var child_div = $(this).find(".li-second-child-div");
                        $(this).addClass("a_point_choice");
                        $(child_i).addClass("a_point_choice");
                        $(child_div).addClass("a_point_choice");
                        $(this).parent().addClass("li_point_choice");
                        if ($(this).hasClass("a_point_choice_other")) {
                            $(this).removeClass("a_point_choice_other");
                            $(child_div).removeClass("a_point_choice_other");
                            $(child_i).removeClass("a_point_choice_other");
                            $(this).parent().removeClass("li_point_choice_other")
                        }
                        $others_a = $parent_li_siblings.find("a");
                        $others_a.each(
                            function () {
                                if ($(this).hasClass("a_point_choice")) {
                                    var child_i = $(this).find('i');
                                    var child_div = $(this).find(".li-second-child-div");
                                    $(this).removeClass("a_point_choice");
                                    $(child_i).removeClass("a_point_choice");
                                    $(child_div).removeClass("a_point_choice");
                                    $(this).parent().removeClass("li_point_choice");
                                    $(this).addClass("a_point_choice_other");
                                    $(child_i).addClass("a_point_choice_other");
                                    $(child_div).addClass("a_point_choice_other");
                                    $(this).parent().addClass("li_point_choice_other");
                                }
                                else {//初始状态，什么class 都没有
                                    if (!$(this).hasClass("a_point_choice_other")) {
                                        var child_i = $(this).find('i');
                                        var child_div = $(this).find(".li-second-child-div");
                                        $(this).addClass("a_point_choice_other");
                                        $(child_i).addClass("a_point_choice_other");
                                        $(child_div).addClass("a_point_choice_other");
                                        $(this).parent().addClass("li_point_choice_other")
                                    }
                                }
                            }
                        );
                    }
                }
            );
        }
    );

    //li 选中变色 结束
    //控制左侧边栏显示或者隐藏开始
    $("#control-left-div").click(
        function () {
            var $sun_i = $(this).find('i');
            var $left_div = $(".top-leve1-left-div");
            var $right_div = $(".top-level-right-div");
            var li_first_list = $("li div:first-child");
            var li_last_list = $("li div:last-child");
            $("#logo_name").toggleClass("li-second-child-div-hidden");
            if ($left_div.hasClass("top-left-wide")) {
                //说明左侧边栏是显示状态，想要变为隐藏状态
                $left_div.removeClass("top-left-wide");
                $left_div.addClass("top-left-narrow");
                $right_div.removeClass("top-right-narrow");
                $right_div.addClass("top-right-wide");
                $sun_i.removeClass("fa-caret-left");
                $sun_i.addClass("fa-caret-right");
                li_first_list.each(
                    function () {
                        $(this).removeClass("li-first-child-div-wide");
                        $(this).addClass("li-first-child-div-narrow");

                    }
                );
                li_last_list.each(
                    function () {
                        $(this).addClass("li-second-child-div-hidden")
                    }
                );
            }
            else {
                $left_div.removeClass("top-left-narrow");
                $left_div.addClass("top-left-wide");
                $right_div.removeClass("top-right-wide");
                $right_div.addClass("top-right-narrow");
                $sun_i.removeClass("fa-caret-right");
                $sun_i.addClass("fa-caret-left");
                li_first_list.each(
                    function () {
                        $(this).removeClass("li-first-child-div-narrow");
                        $(this).addClass("li-first-child-div-wide");
                    }
                );
                li_last_list.each(
                    function () {
                        $(this).removeClass("li-second-child-div-hidden")
                    }
                );

            }
        }
    );
    //控制左侧边栏显示或者隐藏结束
    // 帮助图标 点击 动作开始
    $(".right-top-son-left").click(
        function () {
            alert("本功能正在完善中")
        }
    );
    // 帮助图标 点击 动作结束
    //用户中心开始
    $(".right-top-son-right").click(
        function () {
            var $user_circle = $("#user-circle");
            $user_circle.toggleClass("center_hidden")
        }
    );
    $(window).scroll(
        function () {
            var scro_top = $(window).scrollTop();
            if (scro_top > 1) {
                var $user_circle = $("#user-circle");
                if (!($user_circle.hasClass("center_hidden"))) {
                    $user_circle.addClass("center_hidden")
                }
            }
        }
    );
    //用户中心结束

    // 代码结束...
});