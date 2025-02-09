using Microsoft.AspNetCore.Mvc;
using BookingPaymentAPI.Data;
using BookingPaymentAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace BookingPaymentAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PaymentController : ControllerBase
    {
        private readonly BookingPaymentDbContext _context;

        public PaymentController(BookingPaymentDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<IActionResult> GetAllPayments()
        {
            return Ok(await _context.Payments.ToListAsync());
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetPaymentById(int id)
        {
            var payment = await _context.Payments.FindAsync(id);
            if (payment == null)
                return NotFound(new { message = "Payment not found" });

            return Ok(payment);
        }

        [HttpPost]
        public async Task<IActionResult> CreatePayment(Payment payment)
        {
            _context.Payments.Add(payment);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetAllPayments), new { id = payment.PaymentId }, payment);
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> UpdatePayment(int id, Payment updatedPayment)
        {
            if (id != updatedPayment.PaymentId)
                return BadRequest(new { message = "ID mismatch" });

            var existingPayment = await _context.Payments.FindAsync(id);
            if (existingPayment == null)
                return NotFound(new { message = "Payment not found" });

            // Update fields
            existingPayment.BookingId = updatedPayment.BookingId;
            existingPayment.AmountToPay = updatedPayment.AmountToPay;
            existingPayment.PaymentDate = updatedPayment.PaymentDate;
            existingPayment.ModeId = updatedPayment.ModeId;

            await _context.SaveChangesAsync();
            return Ok(existingPayment);
        }

        // ✅ DELETE - Remove a payment
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePayment(int id)
        {
            var payment = await _context.Payments.FindAsync(id);
            if (payment == null)
                return NotFound(new { message = "Payment not found" });

            _context.Payments.Remove(payment);
            await _context.SaveChangesAsync();
            return Ok(new { message = "Payment deleted successfully" });
        }
    }
}
