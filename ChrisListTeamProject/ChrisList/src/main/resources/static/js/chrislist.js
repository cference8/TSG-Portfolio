//$(document).ready(function () {
//
//    toDelete()
//
//
//});

function toDelete() {
    if (confirm('Are you sure you want to save this thing into the database?')) {
        
        console.log('Thing was saved to the database.');
    } else {
        document.getElementById('deleteKeyword').val('-1');
        console.log('Thing was not saved to the database.');
    }
}