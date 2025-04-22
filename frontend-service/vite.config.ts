import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

const proxyTarget = 'http://localhost:8080';
// https://vite.dev/config/
export default defineConfig({
    plugins: [react()],
    server: {
        proxy: {
            '/users': {
                target: proxyTarget,
                changeOrigin: true,
            },
            '/import-orders': {
                target: proxyTarget,
                changeOrigin: true,
            },
            '/orders': {
                target: proxyTarget,
                changeOrigin: true,
            },
            '/statistics': {
                target: proxyTarget,
                changeOrigin: true,
            },
            '/products': {
                target: proxyTarget,
                changeOrigin: true,
            },
            '/auth': {
                target: proxyTarget,
                changeOrigin: true,
            },
        },
    },
});
