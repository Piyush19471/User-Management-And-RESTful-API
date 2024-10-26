
document.getElementById('logout-btn').addEventListener('click', function (event) {
    event.preventDefault(); // Prevent the default action

    // Show a confirmation dialog
    const userConfirmed = confirm('Are you sure you want to log out?');

    // If the user clicks "OK", redirect to the logout URL
    if (userConfirmed) {
        window.location.href = this.getAttribute('href'); // Redirect to the logout URL
    }
    // If the user clicks "Cancel", do nothing
});


document.getElementById('save-bio').addEventListener('click', function () {
    const bioText = document.getElementById('bio-text').value;
    if (bioText) {
        alert('Bio saved: ' + bioText);
    } else {
        alert('Please enter your bio before saving.');
    }
});

document.getElementById('dark-mode-btn').addEventListener('click', function () {
    document.body.classList.toggle('dark-mode');
    const btnText = document.body.classList.contains('dark-mode') ? 'Toggle Light Mode' : 'Toggle Dark Mode';
    document.getElementById('dark-mode-btn').innerText = btnText;
});

