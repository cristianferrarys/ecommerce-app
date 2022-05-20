run-docker:
	docker build -f Dockerfile -t ecommerce:1.0 .
	docker run -i --rm --name ecommerce -p 8080:8080 ecommerce:1.0

docker-rmi:
	docker rmi $$(docker images -f "dangling=true" -q)