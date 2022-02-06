<!DOCTYPE html>
<html charset="utf-8" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Error</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-success alert-dismissable">

                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                    ×
                </button>
                <h4>
                    Alert!
                </h4> <strong>${msg}</strong><a href="${url}" class="alert-link">Auto turn to next page, after 3seconds</a>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    setTimeout('location.href="${url}"', 3000);
</script>
</html>

