import { useState } from 'react';
import { Container, TextField, Button, Typography, Box } from '@mui/material';
import axios from 'axios';

export default function RegisterPage() {
    const [form, setForm] = useState({
        name: '',
        email: '',
        password: '',
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const res = await axios.post(
                'http://localhost:3000/api/register',
                form
            );
            alert('Đăng ký thành công!');
            console.log(res.data);
        } catch (err) {
            alert('Đăng ký thất bại.');
            console.error(err);
        }
    };

    return (
        <Container maxWidth="sm">
            <Box mt={5}>
                <Typography variant="h4" gutterBottom>
                    Đăng ký
                </Typography>
                <form onSubmit={handleSubmit}>
                    <TextField
                        fullWidth
                        label="Họ tên"
                        name="name"
                        margin="normal"
                        value={form.name}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        fullWidth
                        label="Email"
                        name="email"
                        type="email"
                        margin="normal"
                        value={form.email}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        fullWidth
                        label="Mật khẩu"
                        name="password"
                        type="password"
                        margin="normal"
                        value={form.password}
                        onChange={handleChange}
                        required
                    />
                    <Button
                        type="submit"
                        variant="contained"
                        color="primary"
                        fullWidth
                        sx={{ mt: 2 }}
                    >
                        Đăng ký
                    </Button>
                </form>
            </Box>
        </Container>
    );
}
