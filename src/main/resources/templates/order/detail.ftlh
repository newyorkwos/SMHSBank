<!DOCTYPE html>
<html charset="utf-8" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Order Detail</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <table class="table table-sm table-bordered">
                <thead>
                <tr>
                    <th>OrderId</th>
                    <th>Total Amount</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${orderMaster.orderId}</td>
                    <td>${orderMaster.orderAmount}</td>
                </tr>
                </tbody>
            </table>
        </div>
            <div class="col-md-12">
                <table class="table table-sm table-bordered">
                <thead>
                <tr class="table-active">
                    <td>
                        Production Id
                    </td>
                    <td>
                        Production Name
                    </td>
                    <td>
                        Production price
                    </td>
                    <td>
                        Production Quantity
                    </td>
                    <td>
                        Amount
                    </td>
                </tr>
                </thead>
                <tbody>
                <#list orderMaster.orderDetails as orderDetail>
                    <tr>
                        <td>${orderDetail.productInfo.productId}</td>
                        <td>${orderDetail.productInfo.productName}</td>
                        <td>${orderDetail.productInfo.productPrice}</td>
                        <td>${orderDetail.productQuantity}</td>
                        <td>${orderDetail.productQuantity* orderDetail.productInfo.productPrice}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
            </div>

        <#--操作-->
            <div class="col-md-12 column">
                <#if orderMaster.getOrderStatusEnum().message == "新订单">
                    <a href="/seller/order/finish?orderId=${orderMaster.orderId}" type="button" class="btn btn-default btn-primary">Finished</a>
                    <a href="/seller/order/cancel?orderId=${orderMaster.orderId}" type="button" class="btn btn-default btn-danger">Cancel</a>
                </#if>
            </div>
        </div>
    </div>
</div>

<script src="/js/bootstrap.min.css"></script>
<script src="/js/jquery.min.css"></script>
<script src="/js/popper.min.css"></script>
</body>
</html>
