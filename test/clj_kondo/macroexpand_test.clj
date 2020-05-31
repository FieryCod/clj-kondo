(ns clj-kondo.macroexpand-test
  (:require
   [clj-kondo.test-utils :refer [lint! assert-submaps]]
   [clojure.java.io :as io]
   [clojure.test :refer [deftest testing is]]))

(deftest macroexpand-test
  (assert-submaps
   '({:file "corpus/macroexpand.clj", :row 20, :col 7, :level :error, :message "Expected: number, received: keyword."}
     {:file "corpus/macroexpand.clj", :row 22, :col 1, :level :error, :message "No sym and val provided [at line 5, column 7]"}
     {:file "corpus/macroexpand.clj", :row 64, :col 48, :level :warning, :message "unused binding tree"}
     {:file "corpus/macroexpand.clj", :row 72, :col 1, :level :warning, :message "Missing catch or finally in try"})
   (lint! (io/file "corpus" "macroexpand.clj")
          {:linters {:unused-binding {:level :warning}
                     :type-mismatch {:level :error}}})))
