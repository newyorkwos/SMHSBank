<html>
<#include "../common/header.ftlh">
<body>
<div class="wrapper d-flex align-items-stretch">
    <#include "../common/nav.ftlh">

    <!-- Page Content  -->
    <div id="content" class="p-4 p-md-5 pt-5">
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

<script src="/js/jquery.min.js"></script>
<script src="/js/popper.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/main.js"></script>

</body>
</html>
