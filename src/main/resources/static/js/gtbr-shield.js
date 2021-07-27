$(document).on('submit', '.form-ajax', function (event) {
    var form = event.target;
    ajaxSubmitForm(form);
    return false;
});

function ajaxSubmitForm(form) {
    var container = $(form).attr('data-target');
    var containerId = '#' + container;
    var url = $(form).attr('action');

    $.ajax({
        contentType: "application/json",
        type: "POST",
        url: url,
        data: JSON.stringify($(form).serializeObject()), // serializes the form's elements.
        success: function (data) {
            $(containerId).html(data);
        }
    })

        .fail(function () {
        });
}

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

$(function() {
    $('form.login').on('submit', function(e) {
        e.preventDefault();

        var formData = $(this).serializeObject();
        console.log(formData);
        $('.datahere').html(formData);
    });
});