;;;;
;;;;   Copyright (c) Ludger Solbach. All rights reserved.
;;;;
;;;;   The use and distribution terms for this software are covered by the
;;;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;;;   which can be found in the file license.txt at the root of this distribution.
;;;;   By using this software in any fashion, you are agreeing to be bound by
;;;;   the terms of this license.
;;;;
;;;;   You must not remove this notice, or any other, from this software.
;;;;

(ns org.soulspace.cmp.fop
  (:require [clojure.java.io :as io]
            [org.soulspace.clj.java.beans :as bean])
  (:import [javax.xml.parsers SAXParserFactory SAXParser]
           [org.apache.fop.apps FOUserAgent Fop FopConfParser FopFactoryBuilder FopFactory MimeConstants]))

;;
;; PDF generation with Apache FOP
;;

(defn new-fop-factory
  "Creates a new FopFactory instance."
  ([]
    (new-fop-factory (io/resource "fop.xconf")))
  ([filename & opts]
    (let [fc (FopConfParser. (io/as-file filename))
          fb (.getFopFactoryBuilder fc)]
      (if opts
        (bean/set-properties! fb opts))
      (.build fb))))

(defn fo-to-pdf
  "Converts fo (file) to a pdf file."
  ([fo pdf-file]
    (fo-to-pdf (new-fop-factory) fo pdf-file))
  ([fop-factory fo pdf-file]
    ; configure foUserAgent as desired
    (with-open [out (io/output-stream pdf-file)]
      (let [foUserAgent (.newFOUserAgent fop-factory)
            fop (.newFop fop-factory MimeConstants/MIME_PDF out)
            sax-factory (SAXParserFactory/newInstance)]
        (.setNamespaceAware sax-factory true)
        (let [parser (.newSAXParser sax-factory)
              dh (.getDefaultHandler fop)]
          (.parse parser fo dh))))))
