# gxt-chrome48
Code to reproduce exception on chrome 48

## To reproduce :
Once the application loaded,

1. Click on **Show Grid**. This will display a tab with a form and grid inside.
2. Select **HIDE** from Show/Hide combobox. 
3. Click **Remove Grid** to remove the grid from the page. 
4. Click **Show Grid** again. 
5. Repeat step 4 and 5 till you get the exception.

The exception will look like this
> com.google.gwt.logging.client.LogConfiguration
 SEVERE: (TypeError) : Cannot read property 'getHeight_21_g$' of nullcom.google.gwt.core.client.JavaScriptException: (TypeError) : Cannot read property 'getHeight_21_g$' of null
 
 You might find the full stacktrace here
 [chrome 48 gxt 2.3.1a-gwt22 exception](https://gist.github.com/alifhaikal88/40506cc0dcbc3094b3d7)