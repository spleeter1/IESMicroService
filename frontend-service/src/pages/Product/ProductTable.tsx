import {
    Box,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
} from '@mui/material';
import { ReactNode } from 'react';

type Product = {
    id: number;
    importPrice: number;
    name: string;
    quantity: number;
    salePrice: number;
    supplierId: number;
    supplierName: string;
};
interface ProductTableProps {
    products: Product[];
    onEdit: (product: Product) => void;
    onDelete: (productId: number) => void;
    onAdd: () => void;
}
const ProductTable = ({
    products,
}: // onEdit,
// onDelete,
// onAdd,
ProductTableProps) => {
    const renderTextCell = (content: ReactNode) => (
        <TableCell
            sx={{
                whiteSpace: 'nowrap',
                overflow: 'hidden',
                textOverflow: 'ellipsis',
                maxWidth: 200,
                verticalAlign: 'middle',
            }}
        >
            {content}
        </TableCell>
    );
    return (
        <Box sx={{ height: '100%' }}>
            <TableContainer
                component={Paper}
                sx={{
                    maxHeight: '100%',
                    overflowY: 'scroll',
                    borderRadius: 2,
                    border: '1px solid #e7e9ef ',
                    scrollbarWidth: 'none',
                }}
            >
                <Table stickyHeader>
                    <TableHead>
                        <TableRow sx={{ backgroundColor: '#f5f6fa' }}>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Product ID
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Name
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Quantity
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Import Price
                            </TableCell>

                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Sale Price
                            </TableCell>
                            <TableCell
                                sx={{
                                    backgroundColor: '#f5f6fa',
                                    textAlign: 'center',
                                }}
                            >
                                Actions
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {products.length === 0 || null ? (
                            <TableRow>
                                <TableCell colSpan={6} align="center">
                                    No suppliers selected
                                </TableCell>
                            </TableRow>
                        ) : (
                            products.map(product => (
                                <TableRow key={product.id}>
                                    {renderTextCell(product.id)}
                                    {renderTextCell(product.name)}
                                    {renderTextCell(product.quantity)}
                                    {renderTextCell(product.importPrice)}
                                    {renderTextCell(product.salePrice)}
                                    <TableCell sx={{ display: 'flex', gap: 1 }}>
                                        <button>Edit</button>
                                        <button>Delete</button>
                                    </TableCell>
                                </TableRow>
                            ))
                        )}
                        <TableRow>
                            <TableCell colSpan={3} align="right">
                                <button onClick={() => {}}>Add Product</button>
                            </TableCell>
                            <TableCell colSpan={3} align="left">
                                <button onClick={() => {}}>Save</button>
                            </TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};
export default ProductTable;
