$(document).ready(function () {
    let isDarkMode = false;

    $("#dark-mode-btn").click(function () {
        isDarkMode = !isDarkMode;
        if (isDarkMode) {
            $("body").addClass("dark-mode");
            $("#dark-mode-btn").text("Light Mode");
        } else {
            $("body").removeClass("dark-mode");
            $("#dark-mode-btn").text("Dark Mode");
        }
    });

    // Show contact popup                                 
    $("#contact-link").click(function () {
        $("#contact-popup").fadeIn();
    });

    // Show about popup
    $("#about-link").click(function () {
        $("#about-popup").fadeIn();
    });

    // Close popups
    $(".close-popup-btn").click(function () {
        $(".popup-container").fadeOut();
    });

    // News page redirection with Sign In/Sign Up condition              
    $("#news-link").click(function () {
        if (confirm("Please sign up or sign in first.")) {
            window.location.href = "logPage";
        }
    });
});