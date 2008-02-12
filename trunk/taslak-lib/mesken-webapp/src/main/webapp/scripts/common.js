/* bu dosyayi omer yazdi*/
function checkAll(theForm, name) {
    for (var i = 0; i < theForm.elements.length; i++) {
        var e = theForm.elements[i];
        var eName = e.name;
        if (eName == name) {
            e.checked = theForm.allbox.checked;
        }
    }
}