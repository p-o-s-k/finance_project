function customerDetails(){
    var customer=sessionStorage.getItem("customerDetails");
    let data=`<tr><td>${customer.customerId}</td><td>${customer.customerFirstName}</td>
    <td>${customer.customerLastName}</td><td>${customer.customerAddress}</td></tr>`;
    document.getElementById("customerDetailsBody").innerHTML=data;
}
