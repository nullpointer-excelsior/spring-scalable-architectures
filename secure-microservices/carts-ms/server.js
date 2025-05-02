const http = require('http');

const server = http.createServer((req, res) => {
    let body = '';

    // Collect the request body
    req.on('data', chunk => {
        body += chunk.toString(); // Convert Buffer to string
    });

    req.on('end', () => {
        // Print method, headers, and body
        console.log('Method:', req.method);
        console.log('Headers:', req.headers);
        console.log('Body:', body);

        res.writeHead(200, {'Content-Type': 'text/plain'});
        res.end('Request received');
    });
});

const PORT = 8082; // Changed port to 8082
server.listen(PORT, () => {
    console.log(`Server is listening on port ${PORT}`);
});
