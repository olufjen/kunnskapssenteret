function confirmationDelete(link, text) {
	if (confirmDelete(text)) {
		link.fireEvent('onclick');
		return true;
	}
	return false;
}

function confirmDelete(text) {
	var ans = confirm(text);
	if (ans == true) {
		return true;
	} else {
		return false;
	}
}