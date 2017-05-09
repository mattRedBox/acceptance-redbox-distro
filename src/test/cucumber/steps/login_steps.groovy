package steps

import au.com.redboxresearchdata.page.HomePage

import static cucumber.api.groovy.EN.*

Given(~/^I am on the home page$/) { ->
    to HomePage
    at HomePage
}

When(~/^I click on login$/) { ->
    page.showLoginDialog()
}

Then(~/^I should see the login dialog$/) { ->
    page.loginForm.isDisplayed()
}

When(~/^I am on the login dialog$/) { ->
    page.showLoginDialog()
}

When(~/^I enter username "([^"]*)" and password "([^"]*)"$/) { String arg1, String arg2 ->
    page.login(arg1, arg2)
}

Then(~/^I am logged in$/) { ->
    page.isLoggedIn()
}
