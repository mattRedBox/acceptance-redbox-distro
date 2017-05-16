package au.com.redboxresearchdata.page

import geb.Page

/**
 * @author <a href="matt@redboxresearchdata.com.au">Matt Mulholland</a>
 * Created on 29/01/2017.
 */
class HomePage extends Page {
    static URL = ""
    static at = { $("h2#page-heading").text() == "Everything" }

    static content = {
        loginLink(required: false) { $("#user-info .login-now") }
        loginForm { $("form#login") }
        loginName { loginForm.find("#username") }
        loginPass { loginForm.find("input[id='password']") }
        submit { loginForm.find("input[id='login-submit']") }
        logoutLink { $("#user-info #logout-now") }
    }

    def login(String username, String password) {
        loginName = username
        loginPass = password
        submit.click()
    }

    def showLoginDialog() {
        if (!loginForm.isDisplayed()) {
            def result = loginLink.click()
            print result
        }
    }

    def isLoggedIn() {
        waitFor { logoutLink }.isDisplayed()
        !loginForm.isDisplayed()
        !loginLink.isDisplayed()
    }

    def isLoginDisplayed() {
        loginForm.isDisplayed()
    }
}
