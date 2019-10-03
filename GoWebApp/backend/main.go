package main

import (
	"encoding/json"
	"fmt"
	"net/http"
)

func setupRoutes() {
	fs := http.FileServer(http.Dir("assets/"))
	http.HandleFunc("/api/", func(w http.ResponseWriter, r *http.Request) {
		w.Header().Set("Access-Control-Allow-Headers", "Accept, Content-Type, Content-Length, Accept-Encoding, X-CSRF-Token, Authorization")
		w.Header().Set("Access-Control-Allow-Origin", "*")
		w.Header().Set("Content-Type", "application/json; charset=utf-8")
		json.NewEncoder(w).Encode(http.StripPrefix("/api/", fs))
	})
}

func main() {
	fmt.Println("Nearby Finder App v0.01")
	setupRoutes()
	http.ListenAndServe(":8080", nil)
}
