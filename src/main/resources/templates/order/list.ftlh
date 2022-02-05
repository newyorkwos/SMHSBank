<!DOCTYPE html>
<html charset="utf-8" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>OrderMaster List</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-sm table-bordered">
                <thead>
                <tr>
                    <th>OrderId</th>
                    <th>Customer Name</th>
                    <th>Customer Address</th>
                    <th>Customer Phone</th>
                    <th>Total Amount</th>
                    <th>Order Status</th>
                    <th>Pay Status</th>
                    <th>Update Time</th>
                    <th colspan="2">Edit</th>
                </tr>
                </thead>
                <tbody>
                <#list orderMasterPage.content as orderMaster>
                    <tr>
                        <td>${orderMaster.orderId}</td>
                        <td>${orderMaster.customer.lastname},${orderMaster.customer.middleName}-${orderMaster.customer.firstName}</td>
                        <td>${orderMaster.customer.address}</td>
                        <td>${orderMaster.customer.phoneNumber}</td>
                        <td>${orderMaster.orderAmount}</td>
                        <td>${orderMaster.getOrderStatusEnum().message}</td>
                        <td>${orderMaster.getPayStatusEnum().message}</td>
                        <td>${orderMaster.updateTime}</td>
                        <td><a href="/seller/order/detail?orderId=${orderMaster.orderId}">Detail</a></td>
                        <td>
                            <#if orderMaster.getOrderStatusEnum().message == "新订单">
                                <a href="/seller/order/cancel?orderId=${orderMaster.orderId}">Cancel</a>
                            </#if>
                        </td>
                    </tr>
                </#list>

                </tbody>
            </table>
        </div>
        <#--分頁-->
        <div class="col-md-12">

                <ul class="pagination justify-content-end" >
                    <#if currentPage lte 1>
                        <li class="disabled page-item ">
                            <a class="page-link"  href="#">Previous</a>
                        </li>
                    <#else>
                        <li>
                            <a class="page-link"  href="/seller/order/list?page=${currentPage-1}&size=${size}">Previous</a>
                        </li>
                    </#if>
                    <#list 1..orderMasterPage.getTotalPages() as index>
                        <#if currentPage ==index>
                            <li class="disabled page-item ">
                                <a class="page-link" href="#">${index}</a>
                            </li>
                        <#else>
                            <li>
                                <a class="page-link" href="/seller/order/list?page=${index}&size=${size}">${index}</a>
                            </li>
                        </#if>
                    </#list>
                    <#if currentPage gte orderMasterPage.getTotalPages()>
                        <li class="disabled page-item"><a class="page-link" href="#">Next</a></li>
                    <#else>
                        <li>
                            <a class="page-link" href="/seller/order/list?page=${currentPage + 1}&size=${size}">Next</a>
                        </li>
                    </#if>
                </ul>

        </div>

    </div>
</div>
<script src="/js/bootstrap.min.css"></script>
<script src="/js/jquery.min.css"></script>
<script src="/js/popper.min.css"></script>
</body>
</html>





