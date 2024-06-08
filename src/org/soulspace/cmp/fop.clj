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
    (let [fc (FopConfParser. (.getAbsoluteFile (io/as-file filename)))
          fb (.getFopFactoryBuilder fc)]
      (when opts
        (bean/set-properties! fb opts))
      (.build fb))))

(defn new-user-agent
  "Creates a new FOUserAgent from the `fop-factory` to customize output properties."
  ([fop-factory & opts]
   (let [fo-user-agent (.newFOUserAgent fop-factory)]
     (when (seq opts)
       (bean/set-properties! fo-user-agent opts))
     fo-user-agent)))

(defn fo-to-pdf
  "Creates the `pdf-file` from the `fo` input. Optionally takes the `fop-factory`
   and an `opts` map to set via the created FOUserAgent.
   See the Apache FOP documentations for details."
  ([fo pdf-file]
   (fo-to-pdf (new-fop-factory) fo pdf-file))
  ([fop-factory fo pdf-file]
   (with-open [out (io/output-stream pdf-file)]
     (let [foUserAgent (new-user-agent fop-factory)
           fop (.newFop fop-factory MimeConstants/MIME_PDF foUserAgent out)
           sax-factory (SAXParserFactory/newInstance)]
       (.setNamespaceAware sax-factory true)
       (let [parser (.newSAXParser sax-factory)
             dh (.getDefaultHandler fop)]
         (.parse parser fo dh)))))
  ([fop-factory fo pdf-file opts]
    ; configure foUserAgent as desired
   (with-open [out (io/output-stream pdf-file)]
     (let [foUserAgent (new-user-agent fop-factory opts)
           fop (.newFop fop-factory MimeConstants/MIME_PDF foUserAgent out)
           sax-factory (SAXParserFactory/newInstance)]
       (.setNamespaceAware sax-factory true)
       (let [parser (.newSAXParser sax-factory)
             dh (.getDefaultHandler fop)]
         (.parse parser fo dh))))))

(comment
  (new-fop-factory) 
  (new-fop-factory "fop.xconf")
  (new-user-agent (new-fop-factory))
  ;
  )