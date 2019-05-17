<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
        <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
        <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="container contact-form">
            <div class="contact-image">
                <img src="https://image.ibb.co/kUagtU/rocket_contact.png" alt="rocket_contact"/>
            </div>
            <form method="post" id="emailform">
                <h3>Drop Us a Message</h3>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input type="email" name="txtName" class="form-control" id="to" placeholder="To *" value="" required/>
                        </div>
                        <div class="form-group">
                            <input type="email" name="txtEmail" class="form-control" id="cc" placeholder="CC " value="" />
                        </div>
                        <div class="form-group">
                            <input type="email" name="txtPhone" class="form-control" id="bcc" placeholder="BCC " value="" />
                        </div>
                        <div class="form-group">
                            <input type="text" name="txtPhone" class="form-control" id="subject" placeholder="Subject *" value="" required/>
                        </div>
                        <div class="form-group">
                            <input type="submit" name="btnSubmit" class="btnContact" value="Send Message" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <textarea name="txtMsg" class="form-control" id="message" placeholder="Your Message *" style="width: 100%; height: 200px;" required></textarea>
                        </div>
                    </div>
                </div>
            </form>
            <div class="msg">
                <div class="result">

                </div>
            </div>
        </div>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
        <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
        <script>
            $(".msg").hide();
            $("#message").summernote({
                height: 120
            });
            $(function () {
                $("body").on("submit", "#emailform", function () {
                    $.ajax({
                        url: "rest/SentEmail",
                        method: "POST",
                        data: {
                            emailTo: $("#to").val(),
                            ccTo: $("#cc").val(),
                            bccTo: $("#bcc").val(),
                            subject: $("#subject").val(),
                            message: $("#message").val()
                        }, success: function (data, textStatus, jqXHR) {
                            $(".msg").show();
                            if (data.responseCode === 1) {
                                $(".msg").css("background", "#a6eca6");
                                $(".result").text(data.msg);
                            } else {
                                $(".msg").css("background", "#f2aea3");
                                $(".result").text(data.msg);
                            }
                        }, error: function (jqXHR, textStatus, errorThrown) {
                            alert("Error Occured");
                        }
                    });
                    return false;
                });
            });
        </script>
    </body>
</html>
