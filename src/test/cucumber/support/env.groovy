package support

import static cucumber.api.groovy.Hooks.*

import geb.Browser
import geb.binding.BindingUpdater


def bindingUpdater
def currentBrowser

Before() { scenario ->
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()
}

After() { scenario ->
    bindingUpdater.remove()
}