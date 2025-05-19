import { useState } from 'react';
import { Container, TextField, Button, Typography, Box } from '@mui/material';
import axios from 'axios';

export default function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const res = await axios.post('http://localhost:3000/api/login', {
                email,
                password,
            });
            alert('Đăng nhập thành công!');
            console.log(res.data);
        } catch (err) {
            alert('Đăng nhập thất bại.');
            console.error(err);
        }
    };

    return (
        <Container maxWidth="sm">
            <Box mt={5}>
                <Typography variant="h4" gutterBottom>
                    Đăng nhập
                </Typography>
                <form onSubmit={handleSubmit}>
                    <TextField
                        fullWidth
                        label="Email"
                        margin="normal"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                        type="email"
                        required
                    />
                    <TextField
                        fullWidth
                        label="Mật khẩu"
                        margin="normal"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        type="password"
                        required
                    />
                    <Button
                        type="submit"
                        variant="contained"
                        color="primary"
                        fullWidth
                        sx={{ mt: 2 }}
                    >
                        Đăng nhập
                    </Button>
                </form>
            </Box>
        </Container>
    );
}
