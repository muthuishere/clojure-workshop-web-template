(defproject hello-web-1 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 [org.clojure/clojure "1.10.1"]

                 ;; Jetty
                 ;[ring/ring-jetty-adapter "1.7.1"]

                 ;http-kit
                 [http-kit "2.5.0"]

                 ;ring
                 [ring/ring-core "1.7.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]


                 ;compojure
                 ;[compojure "1.6.1"]
                 [metosin/compojure-api "1.1.13"]
                 [metosin/ring-http-response "0.9.3"]

                 ]
  :repl-options {:init-ns hello-web-1.core}

  ;For Live reloading
  ;lein ring server-headless
  :plugins [
            [lein-ring "0.12.5"]
            ]

  :ring {
         :handler hello-web-1.core/app
         }
  )
