$(document).ready(function () {

    loadItems();

    $('#vendItem').click(function (event) {
        var messageBox = $('#messages');
        var changeBox = $('#change');
        var itemSelected = $('#itemInput').val();

        $.ajax({
            type: 'POST',
            url: 'http://tsg-vending.herokuapp.com/money/' + balance + '/item/' + itemSelected,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (vend) {
                var quarters = vend.quarters
                var dimes = vend.dimes
                var nickels = vend.nickels
                var pennies = vend.pennies
                changeBox.val('Q: ' + quarters + ' D: ' + dimes + ' N: ' + nickels + ' P: ' + pennies);
                $('#balance').val('0.00');
                balance = 0.00;
                loadItems();
                messageBox.val('Thank You!');

            },
            error: function (jqXHR, textStatus, errorThrown) {
                msg = jqXHR.responseJSON.message;
                messageBox.val(msg);
                loadItems();
            }

        });
    });
});

var balance = 0.00;

function loadItems() {

    clearLoadItems();

    var itemButtons = $('#itemButtons');

    $.ajax({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function (items) {
            $.each(items, function (index, item) {
                var itemSelect = '<button style="width: 32%; height: 200px" id="btnClick" onclick="selectedItem(' + item.id + ' )">';
                itemSelect += item.name + '<br>';
                itemSelect += '$' + item.price.toFixed(2) + '<br>';
                itemSelect += 'Stock: ' + item.quantity;
                itemSelect += "</button>";
                itemButtons.append(itemSelect);


            });
        },
        error: function () {
            alert("failed to load items");
        }
    });

}



function clearLoadItems() {
    $('#itemButtons').empty();
}

function selectedItem(val) {
    $("#itemInput").val(val)
}

function returnChange() {
    $('#messages').val('');
    $('#itemInput').val('');
    var quarters;
    var dimes;
    var nickels;
    var pennies;

    pennies = balance * 100;
    quarters = pennies / 25;
    pennies = pennies % 25;
    dimes = pennies / 10;
    pennies = pennies % 10;
    nickels = pennies / 5;
    pennies = pennies % 5;

    $('#change').val('Q: ' + Math.floor(quarters) + ' D: ' + Math.floor(dimes) + ' N: ' + Math.floor(nickels) + ' P: ' + Math.floor(pennies));
    if (balance == 0.00) {
        $('#change').val('');
    } 
    $('#balance').val('0.00');
    balance = 0.00;

    loadItems();

}



function addDollar() {
    balance = (Math.round((balance + 1.00) * 100)) / 100;
    $("#balance").val(balance.toFixed(2));
}
function addQuarter() {
    balance = (Math.round((balance + 0.25) * 100)) / 100;
    $("#balance").val(balance.toFixed(2));
}
function addDime() {
    balance = (Math.round((balance + 0.10) * 100)) / 100;
    $("#balance").val(balance.toFixed(2));
}
function addNickle() {
    balance = (Math.round((balance + 0.05) * 100)) / 100;
    $("#balance").val(balance.toFixed(2));
}


