(defproject my-4clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/test.check "0.10.0-RC1"]
                 [com.velisco/herbert "0.7.0"]]
  :repl-options {:init-ns my-4clojure.core}
  :plugins [[lein-midje "3.2.1"]]
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[midje "1.9.1"]]}})
