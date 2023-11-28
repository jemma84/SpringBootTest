  function limitSelection(selectElement, maxSelection) {
    let selectedOptions = Array.from(selectElement.selectedOptions);
    if (selectedOptions.length > maxSelection) {
      alert('Please select up to ' + maxSelection + ' options.');
      Array.from(selectElement.options).forEach(function (option) {
        option.selected = false;
});
}
}
