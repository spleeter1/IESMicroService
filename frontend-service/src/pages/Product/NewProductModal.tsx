import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField,
    Button,
    Box,
} from '@mui/material';
import { useState, useEffect } from 'react';

interface NewProductModalProps {
    open: boolean;
    onClose: () => void;
    onSave: (product: Omit<Product, 'id' | 'quantity'>) => void;
    supplierId: number;
    supplierName: string;
}

interface Product {
    name: string;
    importPrice: number;
    salePrice: number;
    supplierId: number;
    supplierName: string;
}

const NewProductModal = ({
    open,
    onClose,
    onSave,
    supplierId,
    supplierName,
}: NewProductModalProps) => {
    const [form, setForm] = useState<Omit<Product, 'id' | 'quantity'>>({
        name: '',
        importPrice: 0,
        salePrice: 0,
        supplierId,
        supplierName,
    });

    useEffect(() => {
        if (open) {
            setForm({
                name: '',
                importPrice: 0,
                salePrice: 0,
                supplierId,
                supplierName,
            });
        }
    }, [open, supplierId, supplierName]);

    const handleChange = (
        field: keyof Omit<Product, 'id' | 'quantity'>,
        value: string | number
    ) => {
        setForm(prev => ({ ...prev, [field]: value }));
    };

    const handleSubmit = () => {
        onSave(form);
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Thêm sản phẩm mới</DialogTitle>
            <DialogContent>
                <Box display="flex" flexDirection="column" gap={2} mt={1}>
                    <TextField
                        label="Tên sản phẩm"
                        fullWidth
                        value={form.name}
                        onChange={e => handleChange('name', e.target.value)}
                    />
                    <TextField
                        label="Giá nhập"
                        type="number"
                        fullWidth
                        value={form.importPrice}
                        onChange={e =>
                            handleChange('importPrice', +e.target.value)
                        }
                    />
                    <TextField
                        label="Giá bán"
                        type="number"
                        fullWidth
                        value={form.salePrice}
                        onChange={e =>
                            handleChange('salePrice', +e.target.value)
                        }
                    />
                </Box>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Huỷ</Button>
                <Button variant="contained" onClick={handleSubmit}>
                    Thêm
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default NewProductModal;
