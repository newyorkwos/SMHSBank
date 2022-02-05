<!DOCTYPE html>
<html charset="utf-8" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>OrderMaster List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">


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
                        <td>${orderMaster.customer.lastname}${orderMaster.customer.middleName}${orderMaster.customer.firstName}</td>
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
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>





