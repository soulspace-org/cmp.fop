cmp.fop
=======
The cmp.fop component is a Clojure wrapper for Apache FOP.

It fits nicely with the XSL-FO DSL in [xml.dsl](https://github.com/soulspace-org/xml.dsl).

If you want to generate PDF documents from data in your application, the **traditional** way of achieving it with XSL-FO is
* convert your data to XML
* apply an XSL transformation to generate the XSL-FO output
* use a FO processor like Apache FOP with the generated XSL-FO to generate the PDF file

With the XSL-FO DSL there is **no need** to convert your data to XML and to apply a XSL transformation.
Just use the generated Clojure functions to programm XSL-FO directly with the data in your program.
* use FO DSL functions and clojure control structures to generate XSL-FO directly from your data
* use Apache FOP with the generated XSL-FO to generate the PDF file  via [cmp.fop](https://github.com/soulspace-org/cmp.fop)

It is a lot easier, faster and much less memory intensive, to drop XSL transformation step completely.
You don't have to convert your data to XML and you don't have to write the transformation to XSL-FO
in a *foreign* language (XSL). It's all done in the Clojure code of your application now.

Usage
-----
#### Dependency
[![Clojars Project](https://img.shields.io/clojars/v/org.soulspace.clj/cmp.fop.svg)](https://clojars.org/org.soulspace.clj/cmp.fop)

### Example
Example call to generate a PDF document from a static XSL-FO document.

```
(ns example.fop-test
  (:require [org.soulspace.cmp.fop :as fop]))

(fop/fo-to-pdf "example/fo.xml" "target/test.pdf")
```

Copyright
---------
© 2011-2023 Ludger Solbach

License
-------
[Eclipse Public License 1.0](http://www.eclipse.org/legal/epl-v10.html)

Code Repository
---------------
[CljComponents on GitHub](https://github.com/soulspace-org/cmp.fop)
