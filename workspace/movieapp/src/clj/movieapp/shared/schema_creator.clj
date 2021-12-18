(ns movieapp.shared.schema-creator
  (:require [spec-tools.core :as spec-core]))


(defn create-spec [data-type default-value]
  (spec-core/spec
    {
     :spec data-type
     :swagger/default default-value
     }
    )

  )

(defn create-string-with [default-value]
  (create-spec string? default-value)

  )
