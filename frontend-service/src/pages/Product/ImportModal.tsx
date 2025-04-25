import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField,
    Button,
} from '@mui/material';
import { useEffect, useState } from 'react';
import { SelectedProduct } from './SelectedProductsTable';

interface ImportModalProps {
    open: boolean;
    product: SelectedProduct | null;
    onClose: () => void;
    onSave: (product: SelectedProduct) => void;
}

const ImportModal = ({ open, product, onClose, onSave }: ImportModalProps) => {
    const [form, setForm] = useState<SelectedProduct | null>(null);

    // Khi mở lại modal, gán product vào local state
    useEffect(() => {
        if (product) {
            setForm(product);
        }
    }, [product]);

    // Nếu chưa có dữ liệu thì không hiển thị
    if (!form) return null;

    const handleChange = (field: keyof SelectedProduct, value: number) => {
        setForm(prev => (prev ? { ...prev, [field]: value } : null));
    };

    const handleSubmit = () => {
        if (form) {
            onSave(form);
        }
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Chỉnh sửa sản phẩm</DialogTitle>
            <DialogContent>
                <TextField
                    label="Quantity"
                    type="number"
                    fullWidth
                    sx={{ mb: 2, mt: 1 }}
                    value={form.quantity}
                    onChange={e => handleChange('quantity', +e.target.value)}
                />
                {/* <TextField
                    label="Sale Price"
                    type="number"
                    fullWidth
                    value={form.salePrice}
                    onChange={e => handleChange('salePrice', +e.target.value)}
                /> */}
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Huỷ</Button>
                <Button onClick={handleSubmit} variant="contained">
                    Lưu
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default ImportModal;
