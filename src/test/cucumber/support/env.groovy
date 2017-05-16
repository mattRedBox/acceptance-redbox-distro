package support

import geb.Browser
import geb.binding.BindingUpdater
import org.openqa.selenium.remote.RemoteWebDriver

import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before


def bindingUpdater
Browser currentBrowser

Before() { scenario ->
    currentBrowser = new Browser()
    bindingUpdater = new BindingUpdater(binding, currentBrowser)
    bindingUpdater.initialize()
    if (currentBrowser.driver instanceof RemoteWebDriver) {
        //needed for test listener(s)
        println("sessionId:" + ((RemoteWebDriver) currentBrowser.driver).getSessionId())
    }
}

After() { scenario ->
    bindingUpdater.remove()
}