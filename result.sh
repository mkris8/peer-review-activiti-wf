curl -u admin:admin -H "Content-Type: application/json" http://localhost:8080/runtime/tasks > unformatted.json
cat unformatted.json | python -m json.tool > formatted.json
less formatted.json